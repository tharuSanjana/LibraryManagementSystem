package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.example.bo.custom.AdminBo;
import org.example.bo.custom.BookBo;
import org.example.bo.impl.AdminBoImpl;
import org.example.dto.AdminDto;
import org.example.entity.Branch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class AdminRegistrationFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;
    @FXML
    private Label lblId;

    @FXML
    private ComboBox<String> cmbBranch;

   // AdminBo adminBo = new AdminBoImpl();
    AdminBo adminBo = (AdminBo) BoFactory.boFactory().getBoTypes(BoFactory.BOTypes.ADMIN);


    public void initialize(){
        populateCmbBox();
        generateAdminId();
    }
    public void LoginBtnOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }



    public void saveBtnOnAction(javafx.event.ActionEvent actionEvent) {
        if (validAdmin()) {
            String id = lblId.getText();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String branchId = cmbBranch.getValue();
            String userName = txtUserName.getText();
            String password = txtPassword.getText();

            Branch branch = new Branch();
            branch.setId(branchId);
            var dto = new AdminDto(id,name,email,branch,userName,password);

            boolean flag = adminBo.saveAdmin(dto);

            if(flag){
                new Alert(Alert.AlertType.CONFIRMATION, "Admin saved!").show();
                clearFields();
            }
        }

    }
    private void clearFields() {
        lblId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
    }
    private String generateAdminId(){
        String adminId = null;
        adminId = String.valueOf(adminBo.getGenerateAdminId());
        lblId.setText(adminId);

        return adminId;
    }


    public void cmbBranchIdOnAction(javafx.event.ActionEvent actionEvent) {

    }
    public void populateCmbBox(){
        ArrayList<String> id =  adminBo.getCmbBranchId();
        ObservableList<String> strings = FXCollections.observableArrayList(id);
        cmbBranch.setItems(strings);
    }

    private boolean validAdmin() {
        String id = lblId.getText();
        boolean isIdValid = Pattern.matches("[A][0-9]{3,}", id);
        if (!isIdValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid admin id").show();
            return false;
        }
        String name = txtName.getText();
        boolean isNameValid = Pattern.matches("[A-Za-z]{4,}", name);
        if (!isNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            return false;
        }


        return true;
    }
}
