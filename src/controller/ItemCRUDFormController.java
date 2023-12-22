package controller;

import bo.BoFactory;
import bo.custom.ItemBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ItemDto;
import dto.tm.ItemTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.custom.impl.ItemDaoImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class ItemCRUDFormController {
    public AnchorPane itemFormContext;
    public JFXTextField tf_ItemCode;
    public JFXTextField tf_ItemDescription;
    public JFXTextField tf_UnitPrice;
    public JFXTextField tf_Qty;
    public JFXButton btn_AddItem;
    public TextField tf_Search;
    public JFXButton btn_Update;
    public JFXButton btn_AddNew;
    public TableView tbl_Item;
    public TableColumn col_ItemCode;
    public TableColumn col_ItemDescription;
    public TableColumn col_UnitPrice;
    public TableColumn col_Qty;
    public TableColumn col_Option;

    //ItemDaoImpl itemModal = new ItemDaoImpl();
    ItemBo bo = BoFactory.getInstance().getBo(BoFactory.BoTypes.ITEM);


    public void initialize(){
        col_ItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        col_ItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_UnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        col_Qty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        col_Option.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadItemTable();
        btn_Update.setDisable(true);
        tbl_Item.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((ItemTm) newValue);
        });
    }

    private void setData(ItemTm newValue) {
        if (newValue != null) {
            tf_ItemCode.setEditable(false);
            tf_ItemCode.setText(newValue.getCode());
            tf_ItemDescription.setText(newValue.getDescription());
            tf_UnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
            tf_Qty.setText(String.valueOf(newValue.getQtyOnHand()));
            btn_Update.setDisable(false);
            btn_AddItem.setDisable(true);
        }
    }

    private void loadItemTable() {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();
        try {
            List<ItemDto> dtoList = bo.getAllItems() ;

            for (ItemDto dto : dtoList) {
                Button btn = new Button("Delete");

                ItemTm item = new ItemTm(
                        dto.getCode(),
                        dto.getDesc(),
                        dto.getUnitPrice(),
                        dto.getQty(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    Alert alert = new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "Are you sure?",
                            ButtonType.YES,
                            ButtonType.NO
                    );
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {
                        try {
                            if (bo.deleteItem(dto.getCode())) {
                                loadItemTable();
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Item Deleted!..").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING,
                                        "Try Again!..").show();
                            }
                        } catch (Exception e) {
                            new Alert(Alert.AlertType.ERROR,
                                    "Error..").show();
                        }
                    }
                });

                tmList.add(item);
            }
            tbl_Item.setItems(tmList);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteItem(String code) {
        try {
            boolean isDeleted = bo.deleteItem(code);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Item Deleted!").show();
                loadItemTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItemOnAction(ActionEvent actionEvent) {
        ItemDto itemDto = new ItemDto(tf_ItemCode.getText(), tf_ItemDescription.getText()
                , Double.parseDouble(tf_UnitPrice.getText()), Integer.parseInt(tf_Qty.getText()));
        try {
            boolean isSaved = bo.saveItem(itemDto);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Item Saved!").show();
                loadItemTable();
                clearFields();
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            new Alert(Alert.AlertType.ERROR, "Duplicate Entry").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        tf_ItemCode.clear();
        tf_ItemDescription.clear();
        tf_UnitPrice.clear();
        tf_Qty.clear();
        btn_AddItem.setDisable(false);
        btn_Update.setDisable(true);
    }

    public void updateItemOnAction(ActionEvent actionEvent) {
        ItemDto itemDto = new ItemDto(tf_ItemCode.getText(), tf_ItemDescription.getText()
                , Double.parseDouble(tf_UnitPrice.getText()), Integer.parseInt(tf_Qty.getText()));
        try {
            boolean isUpdated = bo.updateItem(itemDto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated!").show();
                loadItemTable();
                clearFields();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void navigateBackOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashBoardForm");
    }

    public void addNewOnAction(ActionEvent actionEvent) {
        clearFields();
    }



    public void setUI(String viewName) throws IOException {
        Stage stage = (Stage) itemFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + viewName + ".fxml"))));
        String title = viewName.split("F")[0];
        stage.setTitle(title);
    }
}
