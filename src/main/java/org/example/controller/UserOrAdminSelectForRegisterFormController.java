package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class UserOrAdminSelectForRegisterFormController {
    @FXML
    private CheckBox checkBoxAdmin;

    @FXML
    private CheckBox checkBoxUser;
    @FXML
    private AnchorPane root;



    @FXML
    void adminCheckBoxOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/adminRegistrationForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Registration");
        stage.centerOnScreen();
    }

    @FXML
    void userCheckBoxOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/userRegiForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("User Dashboard");
        stage.centerOnScreen();
    }
}
