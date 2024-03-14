package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BranchBo;
import org.example.bo.BranchBoImpl;
import org.example.dto.BranchDto;


public class AdminAddBranchesFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private Button cancelBtn;


    @FXML
    private Label lblId;

    @FXML
    private TextField txtLocation;

   BranchBo branchBo = new BranchBoImpl();


   public void initialize(){
       generateBranchId();
   }
    @FXML
    void cancelBtnOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {

                closeForm();
            } else {

            }
        });
    }
    private void closeForm() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();


    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String id = lblId.getText();
        String location = txtLocation.getText();

        var dto = new BranchDto(id,location);
        boolean flag = branchBo.saveBranch(dto);

        if(flag){
            new Alert(Alert.AlertType.CONFIRMATION, "Branch saved!").show();
            clearFields();
        }
    }
    private void clearFields() {
        lblId.setText("");
        txtLocation.setText("");
    }
    private String generateBranchId(){
        String branchId = null;
        branchId = String.valueOf(branchBo.getGenerateBranchId());
        lblId.setText(branchId);

        return branchId;
    }


}
