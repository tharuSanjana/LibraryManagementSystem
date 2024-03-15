package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.BoFactory;
import org.example.bo.custom.BookBo;
import org.example.bo.custom.UserBo;
import org.example.bo.impl.UserBoImpl;
import org.example.dto.GetUserIdDto;
import org.example.dto.UserDto;

import java.io.IOException;

public class UserProfileFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtBrnchd;

    //UserBo userBo = new UserBoImpl();
    UserBo userBo = (UserBo) BoFactory.boFactory().getBoTypes(BoFactory.BOTypes.USER);


    public void initialize(){
        setUserDetails();
    }
    @FXML
    void updateBtnOnAction(ActionEvent event) {
        String id = txtUserId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();


        var dto = new UserDto(id,name,email,username,password);
        boolean flag = userBo.updateUser(dto);
        if(flag){
            new Alert(Alert.AlertType.CONFIRMATION, "Update User Details").show();
        }
    }
    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/userDashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }



    public  void setUserDetails(){
        GetUserIdDto getUserIdDto = new GetUserIdDto();
        String id = getUserIdDto.getId();

        UserDto userDto = userBo.getUserDetails(id);
        txtUserId.setText(id);
        txtName.setText(userDto.getName());
        txtEmail.setText(userDto.getEmail());
        txtUsername.setText(userDto.getUsername());
        txtPassword.setText(userDto.getPassword());
    }

}
