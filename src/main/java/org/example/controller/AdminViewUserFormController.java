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
import org.example.bo.UserBo;
import org.example.bo.UserBoImpl;
import org.example.dto.UserDto;
import org.example.dto.tm.UserTm;
import org.example.entity.Branch;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class AdminViewUserFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private TableView<UserTm> tblUsersViewAdmin;

    private ObservableList<UserTm> obList = FXCollections.observableArrayList();
    UserBo userBo = new UserBoImpl();
    public void initialize(){
        setCellValueFactory();
        loadAllUsers();
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

    public void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    public  void loadAllUsers(){
        List<UserDto> userList = null;

        userList = userBo.getAllUsers();
        for (UserDto userDto:userList) {
            Branch branch = new Branch();
            String branchId = branch.setId(userDto.getBranch().getId());

            obList.add(new UserTm(userDto.getId(),
                    userDto.getName(),
                    userDto.getEmail(),
                    branchId,
                    userDto.getUsername(),
                    userDto.getPassword()
                   )
            );


        }
        tblUsersViewAdmin.setItems(obList);
    }


    @FXML
    void UpdateBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/adminUpdateUserForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Update User");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void DeleteBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/adminUpdateUserForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Delete User");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }


}
