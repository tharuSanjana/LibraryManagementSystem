package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.BoFactory;
import org.example.bo.custom.BranchBo;
import org.example.bo.impl.BranchBoImpl;
import org.example.dto.BranchDto;

import java.util.regex.Pattern;


public class AdminAddBranchesFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private Button cancelBtn;


    @FXML
    private Label lblId;

    @FXML
    private TextField txtLocation;

   //BranchBo branchBo = new BranchBoImpl();
   BranchBo branchBo = (BranchBo) BoFactory.boFactory().getBoTypes(BoFactory.BOTypes.BRANCH);


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
       if(validBranch()){
           String id = lblId.getText();
           String location = txtLocation.getText();

           var dto = new BranchDto(id,location);
           boolean flag = branchBo.saveBranch(dto);

           if(flag){
               new Alert(Alert.AlertType.CONFIRMATION, "Branch saved!").show();
               clearFields();
           }
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

    private boolean validBranch() {

        String location = txtLocation.getText();
        boolean isTitleValid = Pattern.matches("[A-Za-z]{4,}", location);
        if (!isTitleValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid location").show();
            return false;
        }




        return true;
    }

}
