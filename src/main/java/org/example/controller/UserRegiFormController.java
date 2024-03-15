package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.BoFactory;
import org.example.bo.custom.BookBo;
import org.example.bo.custom.UserBo;
import org.example.bo.impl.UserBoImpl;
import org.example.dto.UserDto;
import org.example.entity.Branch;

import java.io.IOException;
import java.util.ArrayList;

public class UserRegiFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private ComboBox<String> cmbBranch;

    @FXML
    private Label lblId;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    //UserBo userBo =  new UserBoImpl();
    UserBo userBo = (UserBo) BoFactory.boFactory().getBoTypes(BoFactory.BOTypes.USER);

    public void initialize(){
        populateCmbBox();
        generateUserId();
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String id = lblId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String branchId = cmbBranch.getValue();
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        Branch branch = new Branch();
        branch.setId(branchId);
        var dto = new UserDto(id,name,email,branch,username,password);
        boolean flag = userBo.save(dto);

        if (flag){
            new Alert(Alert.AlertType.CONFIRMATION, "User saved!").show();
            clearFields();
        }
    }
    private void clearFields() {
        lblId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtUsername.setText("");
        txtPassword.setText("");


    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/adminDashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }

    public void loginBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }

    public void populateCmbBox(){
        ArrayList<String> id = userBo.getCmbBranchId();
        ObservableList<String> observableList = FXCollections.observableArrayList(id);
        cmbBranch.setItems(observableList);

    }
    private String generateUserId(){
        String userId = null;
        userId = String.valueOf(userBo.generateUserId());
        lblId.setText(userId);

        return userId;
    }
}
