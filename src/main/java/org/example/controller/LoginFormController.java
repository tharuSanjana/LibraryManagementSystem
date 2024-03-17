package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.BoFactory;
import org.example.bo.custom.AdminBo;
import org.example.bo.custom.BookBo;
import org.example.bo.impl.AdminBoImpl;
import org.example.bo.custom.UserBo;
import org.example.bo.impl.UserBoImpl;
import org.example.dao.custom.AdminDao;
import org.example.dao.impl.AdminDaoImpl;
import org.example.dto.AdminDto;
import org.example.dto.GetUserIdDto;
import org.example.dto.LoginDto;
import org.example.dto.UserDto;
import org.example.entity.Admin;

import java.awt.*;
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
    @FXML
    private Button visibleBtn;
    @FXML
    private CheckBox viewPwd;


    AdminDto adminDto = new AdminDto();
    //AdminBo adminBo = new AdminBoImpl();
    AdminBo adminBo = (AdminBo) BoFactory.boFactory().getBoTypes(BoFactory.BOTypes.ADMIN);

    Admin admin = new Admin();
    AdminDao adminDao = new AdminDaoImpl();
   // UserBo userBo = new UserBoImpl();
    UserBo userBo = (UserBo) BoFactory.boFactory().getBoTypes(BoFactory.BOTypes.USER);

    private UserDto loggedInUser;

    public void initialize() {
        // Set the font of the password field to a monospace font
        txtPassword.setFont(Font.font("Monospaced")); // You can replace "Monospaced" with any monospace font
    }
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
        LoginDto loginDto = new LoginDto();
        loginDto.setId(password);

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

    @FXML
    void visibleBtnOnAction(ActionEvent event) {
        txtPassword.setManaged(visibleBtn.isPressed());
        txtPassword.setVisible(visibleBtn.isPressed());
        txtPassword.setDisable(visibleBtn.isPressed());
        txtPassword.setMouseTransparent(visibleBtn.isPressed());
        }

  /*  @FXML
    void showPwdOnAction(ActionEvent event) {
        LoginDto loginDto = new LoginDto();
        String id = loginDto.getId();
        if (viewPwd.isSelected()) {

            String passwordText = txtPassword.getText();
            txtShowPassword.setText(passwordText);
            txtPassword.setVisible(false);
            txtShowPassword.setVisible(true);
        } else {

            txtPassword.setVisible(true);
            txtShowPassword.setVisible(false);
}*/





}









