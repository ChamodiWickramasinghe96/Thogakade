package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDto;
import dto.tm.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.custom.impl.CustomerDaoImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class CustomerCRUDFormController {
    public JFXTextField tf_CustomerID;
    public JFXTextField tf_CustomerName;
    public JFXTextField tf_Address;

    public JFXButton btn_AddCustomer;
    public TextField tf_Search;
    public JFXButton btn_AddNew;
    public TableView tbl_Customer;
    public TableColumn col_CustomerID;
    public TableColumn col_CustomerName;
    public TableColumn col_Address;
    public TableColumn col_Salary;
    public JFXTextField tf_Salary;
    public JFXButton btn_Update;
    public TableColumn col_Option;
    public AnchorPane customerFormContext;

    //CustomerDaoImpl customerModal = new CustomerDaoImpl();

    CustomerBo bo = BoFactory.getInstance().getBo(BoFactory.BoTypes.CUSTOMER);

    public void initialize() {
        col_CustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_CustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        col_Option.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadCustomerTable();

        btn_Update.setDisable(true);
        tbl_Customer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((CustomerTm) newValue);
        });
    }

    private void setData(CustomerTm newValue) {
        if (newValue != null) {
            tf_CustomerID.setEditable(false);
            tf_CustomerID.setText(newValue.getId());
            tf_CustomerName.setText(newValue.getName());
            tf_Address.setText(newValue.getAddress());
            tf_Salary.setText(String.valueOf(newValue.getSalary()));
            btn_Update.setDisable(false);
            btn_AddCustomer.setDisable(true);
        }
    }

    private void loadCustomerTable() {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();
        try {

            for (CustomerDto dto : bo.getAllCustomers()) {
                Button btn = new Button("Delete");

                CustomerTm c = new CustomerTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getSalary(),
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
                            if (bo.deleteCustomer(dto.getId())) {
                                loadCustomerTable();
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Customer Deleted!..").show();
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

                tmList.add(c);
            }
            tbl_Customer.setItems(tmList);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteCustomer(String id) {
        try {
            boolean isDeleted = bo.deleteCustomer(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
                loadCustomerTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void navigateBackOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashBoardForm");
    }

    public void addCustomerOnAction(ActionEvent actionEvent) {
        CustomerDto customerDto = new CustomerDto(tf_CustomerID.getText(), tf_CustomerName.getText()
                , tf_Address.getText(), Double.parseDouble(tf_Salary.getText()));
        try {
            boolean isSaved = bo.saveCustomer(customerDto);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Saved!").show();
                loadCustomerTable();
                clearFields();
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            new Alert(Alert.AlertType.ERROR, "Duplicate Entry").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    private void clearFields() {
        tf_CustomerID.clear();
        tf_CustomerName.clear();
        tf_Address.clear();
        tf_Salary.clear();
        tf_Search.clear();
        btn_Update.setDisable(true);
        btn_AddCustomer.setDisable(false);
    }


    public void addNewOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void searchCustomer() throws SQLException, ClassNotFoundException {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();
        try {

            for (CustomerDto dto : bo.search(tf_Search.getText())) {
                Button btn = new Button("Delete");

                CustomerTm c = new CustomerTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getSalary(),
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
                            if (bo.deleteCustomer(dto.getId())) {
                                loadCustomerTable();
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Doctor Deleted!..").show();
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

                tmList.add(c);
            }
            tbl_Customer.setItems(tmList);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) {
        CustomerDto customerDto = new CustomerDto(tf_CustomerID.getText(), tf_CustomerName.getText()
                , tf_Address.getText(), Double.parseDouble(tf_Salary.getText()));
        try {
            boolean isUpdated = bo.updateCustomer(customerDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Updated!").show();
                loadCustomerTable();
                clearFields();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUI(String viewName) throws IOException {
        Stage stage = (Stage) customerFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + viewName + ".fxml"))));
        String title = viewName.split("F")[0];
        stage.setTitle(title);
    }
}
    