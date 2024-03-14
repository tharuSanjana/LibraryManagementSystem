package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.AdminBo;
import org.example.bo.AdminBoImpl;
import org.example.bo.UserBo;
import org.example.bo.UserBoImpl;
import org.example.dao.AdminDao;
import org.example.dao.AdminDaoImpl;
import org.example.dto.AdminDto;
import org.example.dto.GetUserIdDto;
import org.example.dto.UserDto;
import org.example.entity.Admin;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private CheckBox checkBoxAdmin;

    @FXML
    private CheckBox checkBoxUser;
    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    AdminDto adminDto = new AdminDto();
    AdminBo adminBo = new AdminBoImpl();
    Admin admin = new Admin();
    AdminDao adminDao = new AdminDaoImpl();
    UserBo userBo = new UserBoImpl();
    private UserDto loggedInUser;

    @FXML
    void createAccBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/userOrAdminSelectForRegisterForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Registration");
        stage.centerOnScreen();
    }

    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        boolean isAdmin = checkBoxAdmin.isSelected();
        boolean isUser = checkBoxUser.isSelected();
        AdminDto adminDto = new AdminDto();
        adminDto.setUsername(username);
        adminDto.setPassword(password);
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);

        String id = userBo.getLogUserId(username);
        GetUserIdDto getUserIdDto = new GetUserIdDto();
        getUserIdDto.setId(id);
        System.out.println(id);
        /*UserTransactionFormController userTransactionFormController = new UserTransactionFormController();
        userTransactionFormController.getUserId(id);*/



        boolean isMatched = adminBo.chekUsernamePwd(adminDto);
        boolean flag = userBo.checkUsernamePassword(userDto);
        String userId = "";
        if (isMatched && isAdmin) {

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/adminDashboardForm.fxml"));
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Admin Dashboard");
            stage.centerOnScreen();
        } else if (flag && isUser) {

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/userDashboardForm.fxml"));
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("User Dashboard");
            stage.centerOnScreen();
            /*loggedInUser = userDto;
            UserTransactionFormController userTransactionFormController = new UserTransactionFormController();
            userTransactionFormController.loadBorrowedBooks(loggedInUser);*/
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Username or Password incorrect!").show();
        }
        //GetUserIdDto.setId(userId);
    }
}








