package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.BoFactory;
import org.example.QueryDao;
import org.example.bo.custom.BookBo;
import org.example.bo.custom.TransactionBo;
import org.example.bo.impl.TransactionBoImpl;
import org.example.dto.TransactionDto;
import org.example.dto.tm.TransactionTm;

import java.time.LocalDate;
import java.util.List;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
public class AdminViewNotReturnedBooksFormController {

    //TransactionBo transactionBo = new TransactionBoImpl();
    //TransactionBo transactionBo = (TransactionBo) BoFactory.boFactory().getBoTypes(BoFactory.BOTypes.USERBOOK);

    QueryDao queryDao = new QueryDao();
    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colReceiveDate;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colTranId;

    @FXML
    private TableColumn<?, ?> colUserId;
    @FXML
    private TableColumn<?, ?> colUsername;


    @FXML
    private TableView<TransactionTm> tblNotReturn;

    @FXML
    private Button cancelBtn;
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

    public  void initialize(){
        loadAllNotReturnedBooks();
        setCellValueFactory();
    }

    public void setCellValueFactory(){
        colTranId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colReceiveDate.setCellValueFactory(new PropertyValueFactory<>("reserveDate"));
    }
    public void loadAllNotReturnedBooks() {
        List<TransactionDto> transList = queryDao.getAllUserBorrowBooks();
        ObservableList<TransactionTm> obList = FXCollections.observableArrayList();

        if (transList != null) {
            for (TransactionDto transactionDto : transList) {
                if (transactionDto != null && transactionDto.getBook() != null && transactionDto.getUser() != null) {
                    LocalDate returnDate = transactionDto.getReturnDate();
                    LocalDate dueDate = transactionDto.getReserveDate();


                    if (returnDate != null && dueDate != null) {
                        int comparisonResult = returnDate.compareTo(dueDate);

                        if (comparisonResult < 0) {

                            obList.add(new TransactionTm(
                                    transactionDto.getId(),
                                    transactionDto.getBook().getId(),
                                    transactionDto.getUser().getId(),
                                    transactionDto.getUser().getName(),
                                    transactionDto.getBorrowDate(),
                                    returnDate,
                                    transactionDto.getReserveDate()
                            ));
                        }
                    }

                    tblNotReturn.setItems(obList);
                }
            }
        }
    }
}
