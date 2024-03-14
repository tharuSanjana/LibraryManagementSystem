package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.bo.BranchBo;
import org.example.bo.BranchBoImpl;
import org.example.dto.BranchDto;
import org.example.dto.tm.BranchTm;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class AdminBranchesFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableView<BranchTm> tblBranches;

    BranchBo branchBo = new BranchBoImpl();
    private ObservableList<BranchTm> obList = FXCollections.observableArrayList();

    public void initialize(){
        loadAllBranches();
        setCellValueFactory();

    }
    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/adminDashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }

    @FXML
    void addBranchesBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/adminAddBranchesForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Branch");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    public void loadAllBranches(){
        ArrayList<BranchDto> branchList = null;

        branchList = branchBo.getAllBranches();
        for (BranchDto branchDto:branchList) {



            obList.add(new BranchTm(branchDto.getId(),
                   branchDto.getLocation()
                   )
            );


        }
        tblBranches.setItems(obList);
    }

    public void  setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
    }


    @FXML
    void deleteBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/adminUpdateBranchForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Delete Branch");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/adminUpdateBranchForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Update Branch");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
}
