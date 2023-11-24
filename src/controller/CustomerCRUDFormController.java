package controller;

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
import modal.CustomerModal;
import modal.impl.CustomerModalImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

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

    CustomerModalImpl customerModal = new CustomerModalImpl();

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
            List<CustomerDto> dtoList = customerModal.getAllCustomers();

            for (CustomerDto dto : dtoList) {
                Button btn = new Button("Delete");

                CustomerTm c = new CustomerTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getSalary(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    deleteCustomer(c.getId());
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
            boolean isDeleted = customerModal.removeCustomer(id);
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
        try {
            boolean isSaved = customerModal.addCustomer(new CustomerDto(tf_CustomerID.getText(), tf_CustomerName.getText()
                    , tf_Address.getText(), Double.parseDouble(tf_Salary.getText())));
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
        CustomerDto isAvailable = customerModal.searchCustomer(tf_Search.getText());
        if (isAvailable != null) {

        }
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdated = customerModal.updateCustomer(new CustomerDto(tf_CustomerID.getText(),
                    tf_CustomerName.getText(),
                    tf_Address.getText(),
                    Double.parseDouble(tf_Salary.getText())
            ));
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated!").show();
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
    