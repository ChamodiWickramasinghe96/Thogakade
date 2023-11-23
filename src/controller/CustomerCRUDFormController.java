package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerCRUDFormController {
    public JFXTextField tf_CustomerID;
    public JFXTextField tf_CustomerName;
    public JFXTextField tf_Address;
    public JFXTextField btn_Salary;
    public JFXButton btn_AddCustomer;
    public TextField tf_Search;
    public JFXButton btn_Remove;
    public JFXButton btn_AddNew;
    public TableView tbl_Customer;
    public TableColumn col_CustomerID;
    public TableColumn col_CustomerName;
    public TableColumn col_Address;
    public TableColumn col_Salary;

    public void navigateBackOnAction(ActionEvent actionEvent) {
    }

    public void addCustomerOnAction(ActionEvent actionEvent) {
    }

    public void removeCustomerOnAction(ActionEvent actionEvent) {
    }

    public void addNewOnAction(ActionEvent actionEvent) {
    }
}
