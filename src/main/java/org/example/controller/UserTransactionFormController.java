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

import org.example.BoFactory;
import org.example.bo.custom.BookBo;
import org.example.bo.custom.TransactionBo;
import org.example.bo.impl.TransactionBoImpl;
import org.example.bo.custom.UserBo;
import org.example.bo.impl.UserBoImpl;
import org.example.dto.GetUserIdDto;
import org.example.dto.TransactionDto;
import org.example.dto.UserDto;
import org.example.dto.tm.TransactionTm;

import java.io.IOException;
import java.util.List;

public class UserTransactionFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colReceiveDate;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colReturnStatus;

    @FXML
    private TableColumn<?, ?> colTransId;

    @FXML
    private TableColumn<?, ?> colTitle;
    @FXML
    private TableView<TransactionTm> tblTr;

    //TransactionBo transactionBo = (TransactionBo) new TransactionBoImpl();
    TransactionBo transactionBo = (TransactionBo) BoFactory.boFactory().getBoTypes(BoFactory.BOTypes.USERBOOK);

    UserDto userDto = new UserDto();
    UserBo userBo = new UserBoImpl();
    private ObservableList<TransactionTm> obList = FXCollections.observableArrayList();

    //QueryDao queryDao = new QueryDao();
    public void initialize() {
        setCellValueFactory();
        loadAllUserBorrowBooks();
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

    public void setCellValueFactory() {
        colTransId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colReceiveDate.setCellValueFactory(new PropertyValueFactory<>("reserveDate"));
        colReturnStatus.setCellValueFactory(new PropertyValueFactory<>("isReturn"));
    }

   /* public void  loadBorrowedBooks(){
        GetUserIdDto getUserIdDto = new GetUserIdDto();
        String id = getUserIdDto.getId();

        ObservableList<BorrowDto> obList = FXCollections.observableArrayList();

        List<BorrowDto> dtoList = queryDao.getAllUserBorrowBook(id);

        for (BorrowDto dto : dtoList){
            obList.add(
                    new TransactionTm(
                            dto.getBookId(), dto.getBookName(), dto.getBorrowDate(),dto.getReturnDate()
                    )
            );
        }
        tblTr.setItems(obList);
    }*/

    String generateTransactionId() {
        String transId = null;
        transId = transactionBo.generateTransId();
        // lblId.setText(userId);

        return transId;
    }

    public void loadAllUserBorrowBooks() {
        GetUserIdDto getUserIdDto = new GetUserIdDto();
        String id = getUserIdDto.getId();
        System.out.println(id);
        /*List<TransactionDto> transList = null;

        transList = transactionBo.getAllUserBorrowBooks();
        for (TransactionDto transactionDto:transList) {


            obList.add(new TransactionTm(transactionDto.getId(),
                    transactionDto.getReturnDate(),
                    transactionDto.getReserveDate(),
                    transactionDto.getBorrowDate(),
                    transactionDto.getIsReturn(),
                    transactionDto.getUser().getId(),
                    transactionDto.getBook().getId(),
                    transactionDto.getBook().getTitle()
                    )
            );




        }

            tblTr.setItems(obList);
        }*/


        List<TransactionDto> transList = transactionBo.getAllUserBorrowBooks();

        ObservableList<TransactionTm> obList = FXCollections.observableArrayList();
        for (TransactionDto transactionDto : transList) {
            if (transactionDto.getUser().getId().equals(id)) {
                obList.add(new TransactionTm(transactionDto.getId(),
                                transactionDto.getReturnDate(),
                                transactionDto.getReserveDate(),
                                transactionDto.getBorrowDate(),
                                transactionDto.getIsReturn(),
                                transactionDto.getUser().getId(),
                                transactionDto.getBook().getId(),
                                transactionDto.getBook().getTitle()
                        )
                );
            }
        }


        if (!obList.isEmpty()) {
            tblTr.setItems(obList);
        } else {
            tblTr.getItems().clear();
        }


    }
}
