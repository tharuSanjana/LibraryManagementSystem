package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.bo.BookBo;
import org.example.bo.BookBoImpl;
import org.example.bo.UserBo;
import org.example.bo.UserBoImpl;
import org.example.dto.*;
import org.example.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserReturnBookFormController {
    @FXML
    private ComboBox<String> cmbBookId;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtUserId;
    @FXML
    private Button cancelBtn;

    UserBo userBo = new UserBoImpl();
    BookBo bookBo = new BookBoImpl();
    public void initialize(){
        setUserId();
        populateCmbBox();
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

    public void setUserId(){
        GetUserIdDto getUserIdDto = new GetUserIdDto();
        String id = getUserIdDto.getId();
        txtUserId.setText(id);
    }

    public void populateCmbBox(){
        GetUserIdDto getUserIdDto = new GetUserIdDto();
        String userId = getUserIdDto.getId();
        String isReturn = "no";
       /* User user = new User();
        user.setId(userId);*/
       /* TransactionDto transactionDto = new TransactionDto(isReturn,user);*/
        ArrayList<String> id =  bookBo.getUserCmbBookId(userId,isReturn);

        ObservableList<String> strings = FXCollections.observableArrayList(id);
        cmbBookId.setItems(strings);
    }

    @FXML
    void cmbBookIdOnAction(ActionEvent event) {
        String id = cmbBookId.getValue();
        BookDto bookDto = bookBo.searchBookId(id);

        txtTitle.setText(bookDto.getTitle());
    }

    @FXML
    void returnBtnOnAction(ActionEvent event) {
        String userId = txtUserId.getText();
        String bookId = cmbBookId.getValue();
        String bookName = txtTitle.getText();
        String availability = "yes";
        LocalDate borrowDate = null;
        LocalDate returnDate = null;
        String isReturn = "yes";
        LocalDate reserveDate = LocalDate.now();

        var dto = new ReturnDto(userId,bookId,bookName,availability,borrowDate,returnDate,isReturn,reserveDate);
        boolean flag = userBo.returnBook(dto);

        if (flag){
            new Alert(Alert.AlertType.CONFIRMATION, "Return Success!").show();
        }
    }

}
