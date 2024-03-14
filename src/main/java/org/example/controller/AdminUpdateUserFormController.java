package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.bo.UserBo;
import org.example.bo.UserBoImpl;
import org.example.dto.UserDto;
import org.example.entity.Branch;

import java.util.ArrayList;

public class AdminUpdateUserFormController {
    @FXML
    private ComboBox<String> cmbUserId;

    @FXML
    private TextField txtBranch;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    UserBo userBo = new UserBoImpl();

    public void initialize(){
        populateCmbBox();
    }
    public void cmbUserIdOnAction(ActionEvent actionEvent) {
        String id = cmbUserId.getValue();
        UserDto userDto = userBo.searchUserId(id);
        txtName.setText(userDto.getName());
        txtEmail.setText(userDto.getEmail());
        txtBranch.setText(userDto.getBranch().getId());
    }

    public void okBtnOnAction(ActionEvent actionEvent) {
        String id = cmbUserId.getValue();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String branchId = txtBranch.getText();

        Branch branch = new Branch();
        branch.setId(branchId);

        var dto = new UserDto(id,name,email,branch);
        boolean flag = userBo.updateUser(dto);
        boolean isDeleted = userBo.deleteUser(dto);
        if (flag) {
            new Alert(Alert.AlertType.CONFIRMATION, "User Updated!").show();
            clearFields();
        }

        else if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "User Deleted!").show();
            clearFields();
        }
    }

    public void populateCmbBox(){
        ArrayList<String> id =  userBo.getCmbUserId();
        ObservableList<String> strings = FXCollections.observableArrayList(id);
        cmbUserId.setItems(strings);
    }

    private void clearFields() {

        txtName.setText("");
        txtEmail.setText("");
        txtBranch.setText("");


    }
}
