package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {
    public AnchorPane dashBordFormContext;

    public void setUI(String viewName) throws IOException {
        Stage stage= (Stage) dashBordFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+viewName+".fxml"))));
        String title = viewName.split("F")[0];
        stage.setTitle(title);
    }

    public void customerBtnOnAction(ActionEvent actionEvent) throws IOException {
        setUI("CustomerCRUDForm");

    }

    public void itemBtnOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ItemCRUDForm");
    }

    public void logOutBtnOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SignInForm");
    }


}
