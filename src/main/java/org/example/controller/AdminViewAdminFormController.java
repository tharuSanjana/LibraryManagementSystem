package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.AdminBo;
import org.example.bo.AdminBoImpl;
import org.example.dto.AdminDto;
import org.example.dto.tm.AdminTm;
import org.example.entity.Branch;

import java.io.IOException;
import java.util.List;

public class AdminViewAdminFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private TableView<AdminTm> tblAdminView;
    AdminBo adminBo = new AdminBoImpl();
    private ObservableList<AdminTm> obList = FXCollections.observableArrayList();

    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/adminDashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }

    public void  initialize(){
        setCellValueFactory();
        getAllAdmins();
    }

    public void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    }
    public void getAllAdmins(){
        List<AdminDto> adminList = null;

        adminList = adminBo.getAllAdmins();
        for (AdminDto adminDto:adminList) {
            Branch branch = new Branch();
            String branchId = branch.setId(adminDto.getBranch().getId());
            obList.add(new AdminTm(adminDto.getId(),
                    adminDto.getName(),
                    adminDto.getEmail(),
                    branchId,
                    adminDto.getUsername(),
                    adminDto.getPassword())
            );


        }
        tblAdminView.setItems(obList);
    }
}
