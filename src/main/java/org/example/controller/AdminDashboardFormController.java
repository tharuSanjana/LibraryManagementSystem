package org.example.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminDashboardFormController {

    @FXML
    private AnchorPane adminRootForm;
    @FXML
    private AnchorPane rootAdmin;


    @FXML
    void adminDetailsBtnOnAction(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/adminViewAdminForm.fxml"));
        this.adminRootForm.getChildren().clear();
        this.adminRootForm.getChildren().add(root);
    }

    @FXML
    void booksBtnOnAction(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/adminBookForm.fxml"));
        this.adminRootForm.getChildren().clear();
        this.adminRootForm.getChildren().add(root);
    }

    @FXML
    void branchesBtnOnAction(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/adminBranchesForm.fxml"));
        this.adminRootForm.getChildren().clear();
        this.adminRootForm.getChildren().add(root);
    }

    @FXML
    void transactionBtnOnAction(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/adminTransactionForm.fxml"));
        this.adminRootForm.getChildren().clear();
        this.adminRootForm.getChildren().add(root);
    }

    @FXML
    void userDetailsBtnOnAction(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/adminViewUsersForm.fxml"));
        this.adminRootForm.getChildren().clear();
        this.adminRootForm.getChildren().add(root);
    }

    @FXML
    void addUsersBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/userRegiForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add User");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void logOutBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) rootAdmin.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }
}
