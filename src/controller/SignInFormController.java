package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInFormController {
    public JFXTextField tf_UserName;
    public JFXButton btn_SignIn;
    public AnchorPane singInFormContext;
    public JFXPasswordField tf_Password;

    public void setUI(String viewName) throws IOException {
        Stage stage= (Stage) singInFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+viewName+".fxml"))));
        String title = viewName.split("F")[0];
        stage.setTitle(title);
    }

    public void signInOnAction(ActionEvent actionEvent) throws IOException {
        if (tf_UserName.getText().equalsIgnoreCase("admin") && tf_Password.getText().equals("TestAdmin123@#")) {
            setUI("DashBoardForm");
        }else {
            new Alert(Alert.AlertType.ERROR,"Please enter correct credentials !").show();
            clearFields();
        }
    }

    public void clearFields(){
        tf_UserName.clear();
        tf_Password.clear();
    }
}
