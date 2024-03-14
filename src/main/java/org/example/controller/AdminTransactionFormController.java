package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.bo.TransactionBo;
import org.example.bo.TransactionBoImpl;
import org.example.dto.TransactionDto;
import org.example.dto.tm.TransactionTm;

public class AdminTransactionFormController {
    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colReceiveDate;

    @FXML
    private TableColumn<?, ?> colReceiveStatus;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<TransactionTm> tblAdminTransaction;
    @FXML
    private TableColumn<?, ?> colTransId;

    TransactionBo transactionBo = new TransactionBoImpl();
    private ObservableList<TransactionTm> obList = FXCollections.observableArrayList();

    public void initialize(){
        setCellValueFactory();
        loadAllUserBorrowBooks();
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
    void notReturnedBooksOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/adminViewNotReturnedBooksForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Not Returned Books");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    public void setCellValueFactory(){
        colTransId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colReceiveDate.setCellValueFactory(new PropertyValueFactory<>("reserveDate"));
        colReceiveStatus.setCellValueFactory(new PropertyValueFactory<>("isReturn"));
    }

    public  void loadAllUserBorrowBooks(){
        /*List<TransactionDto> transList = null;

        transList = transactionBo.getAllUserBorrowBookDetails();
        for (TransactionDto transactionDto:transList) {


            obList.add(new TransactionTm(transactionDto.getId(),
                            transactionDto.getBook().getId(),
                            transactionDto.getUser().getId(),
                            transactionDto.getBorrowDate(),
                            transactionDto.getReturnDate(),
                            transactionDto.getReserveDate(),
                            transactionDto.getIsReturn()

                    )
            );




        }

        tblAdminTransaction.setItems(obList);*/
        List<TransactionDto> transList = transactionBo.getAllUserBorrowBooks();
        ObservableList<TransactionTm> obList = FXCollections.observableArrayList();

        if (transList != null) {
            for (TransactionDto transactionDto : transList) {
                if (transactionDto != null && transactionDto.getBook() != null && transactionDto.getUser() != null) {
                    obList.add(new TransactionTm(
                                    transactionDto.getId(),
                                    transactionDto.getBook().getId(),
                                    transactionDto.getUser().getId(),
                                    transactionDto.getBorrowDate(),
                                    transactionDto.getReturnDate(),
                                    transactionDto.getReserveDate(),
                                    transactionDto.getIsReturn()
                            )
                    );
                }
            }
        } else {
            System.out.println("Transaction list is null.");
        }

        tblAdminTransaction.setItems(obList);
    }
}
