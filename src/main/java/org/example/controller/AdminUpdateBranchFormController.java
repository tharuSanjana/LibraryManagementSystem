package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.BoFactory;
import org.example.bo.custom.BookBo;
import org.example.bo.custom.BranchBo;
import org.example.bo.impl.BranchBoImpl;
import org.example.bo.custom.UserBo;
import org.example.bo.impl.UserBoImpl;
import org.example.dto.BranchDto;

import java.util.ArrayList;

public class AdminUpdateBranchFormController {
    @FXML
    private ComboBox<String> cmbBranchId;

    @FXML
    private TextField txtLocation;

    //UserBo userBo = new UserBoImpl();
    //BranchBo branchBo = new BranchBoImpl();
    UserBo userBo = (UserBo) BoFactory.boFactory().getBoTypes(BoFactory.BOTypes.USER);
    BranchBo branchBo = (BranchBo) BoFactory.boFactory().getBoTypes(BoFactory.BOTypes.BRANCH);


    public void initialize(){
    populateCmbBox();
}
    public void cmbBranchIdOnAction(ActionEvent actionEvent) {
      String id = cmbBranchId.getValue();
        BranchDto branchDto  = branchBo.setLocation(id);
        txtLocation.setText(branchDto.getLocation());
    }

    public void okBtnOnAction(ActionEvent actionEvent) {
        String id = cmbBranchId.getValue();
        String location = txtLocation.getText();

        var dto = new BranchDto(id,location);
       boolean flag =  branchBo.updateBranch(dto);
       boolean isDeleted = branchBo.deleteBranch(dto);
        if (flag) {
            new Alert(Alert.AlertType.CONFIRMATION, "Branch Updated!").show();
            clearFields();
        }else  if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Branch Deleted!").show();
            clearFields();
        }
    }

    private void clearFields() {
    txtLocation.setText("");

    }

    public void populateCmbBox(){
        ArrayList<String> id = userBo.getCmbBranchId();
        ObservableList<String> observableList = FXCollections.observableArrayList(id);
        cmbBranchId.setItems(observableList);

    }
}
