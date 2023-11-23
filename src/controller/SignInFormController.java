package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInFormController {
    public JFXTextField tf_UserName;
    public JFXTextField tf_Password;
    public JFXButton btn_SignIn;
    public AnchorPane singInFormContext;
    public void setUI(String viewName) throws IOException {
        Stage stage= (Stage) singInFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+viewName+".fxml"))));
    }

    public void signInOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashBoardForm");
    }
}
