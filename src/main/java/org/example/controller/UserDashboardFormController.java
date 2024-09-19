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

public class UserDashboardFormController {
    @FXML
    private Button profileBtn;

    @FXML
    private Button bookBtn;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootForm;
    @FXML
    private Button transactionBtn;


    public void initialize(){

}
    @FXML
    void booksBtnONAction(ActionEvent event) throws IOException {
      // setForms("/view/userBookForm.fxml");

      /*  Parent parent = FXMLLoader.load(getClass().getResource("/view/userBookForm.fxml"));
        rootForm.getChildren().clear();
        rootForm.getChildren().add(parent);
*/
        resetButtonStyles();
        ((Button) event.getSource()).setStyle("-fx-background-color: #00CCDD;");
        Parent root=FXMLLoader.load(this.getClass().getResource("/view/userBookForm.fxml"));
        this.rootForm.getChildren().clear();
        this.rootForm.getChildren().add(root);


    }

    @FXML
    void transBtnOnAction(ActionEvent event) throws IOException {
        resetButtonStyles();
        ((Button) event.getSource()).setStyle("-fx-background-color: #00CCDD;");
        Parent root=FXMLLoader.load(this.getClass().getResource("/view/userTransactionForm.fxml"));
        this.rootForm.getChildren().clear();
        this.rootForm.getChildren().add(root);
    }

    @FXML
    void profileBtnOnAction(ActionEvent event) throws IOException {
        resetButtonStyles();
        ((Button) event.getSource()).setStyle("-fx-background-color: #00CCDD;");
        Parent root=FXMLLoader.load(this.getClass().getResource("/view/userProfileForm.fxml"));
        this.rootForm.getChildren().clear();
        this.rootForm.getChildren().add(root);
    }

    @FXML
    void logOutBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }

    @FXML
    void returnBookBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/userReturnBookForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Return Book");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();

    }
    private void resetButtonStyles() {
        // Reset all buttons' background color to blue
        bookBtn.setStyle("-fx-background-color:  #4F75FF;");
        transactionBtn.setStyle("-fx-background-color:  #4F75FF;");
        profileBtn.setStyle("-fx-background-color:  #4F75FF;");

    }
}

