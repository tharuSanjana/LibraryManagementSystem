package org.example.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button adminBookBtn;

    @FXML
    private Button adminBranchBtn;

    @FXML
    private Button adminDetailBtn;


    @FXML
    private Button adminTransactionBtn;


    @FXML
    private Button userDetailBookBtn;


    @FXML
    void adminDetailsBtnOnAction(ActionEvent event) throws IOException {
        resetButtonStyles();
        ((Button) event.getSource()).setStyle("-fx-background-color: #00CCDD;");
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/adminViewAdminForm.fxml"));
        this.adminRootForm.getChildren().clear();
        this.adminRootForm.getChildren().add(root);
    }

    @FXML
    void booksBtnOnAction(ActionEvent event) throws IOException {
        resetButtonStyles();
        ((Button) event.getSource()).setStyle("-fx-background-color: #00CCDD;");
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/adminBookForm.fxml"));
        this.adminRootForm.getChildren().clear();
        this.adminRootForm.getChildren().add(root);
    }

    @FXML
    void branchesBtnOnAction(ActionEvent event) throws IOException {
        resetButtonStyles();
        ((Button) event.getSource()).setStyle("-fx-background-color: #00CCDD;");
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/adminBranchesForm.fxml"));
        this.adminRootForm.getChildren().clear();
        this.adminRootForm.getChildren().add(root);
    }

    @FXML
    void transactionBtnOnAction(ActionEvent event) throws IOException {
        resetButtonStyles();
        ((Button) event.getSource()).setStyle("-fx-background-color: #00CCDD;");
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/adminTransactionForm.fxml"));
        this.adminRootForm.getChildren().clear();
        this.adminRootForm.getChildren().add(root);
    }

    @FXML
    void userDetailsBtnOnAction(ActionEvent event) throws IOException {
        resetButtonStyles();
        ((Button) event.getSource()).setStyle("-fx-background-color: #00CCDD;");
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

    private void resetButtonStyles() {
        // Reset all buttons' background color to blue
        adminBookBtn.setStyle("-fx-background-color:  #4F75FF;");
        adminBranchBtn.setStyle("-fx-background-color:  #4F75FF;");
        adminDetailBtn.setStyle("-fx-background-color:  #4F75FF;");
        adminTransactionBtn.setStyle("-fx-background-color:  #4F75FF;");
        userDetailBookBtn.setStyle("-fx-background-color:  #4F75FF;");

    }
}
