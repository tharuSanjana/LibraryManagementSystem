package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.bo.BookBo;
import org.example.bo.BookBoImpl;
import org.example.dto.BookDto;
import org.example.entity.Branch;

import java.util.ArrayList;

public class AdminUpdateBookFormController {
    @FXML
    private ComboBox<String> cbBookId;

    @FXML
    private TextField txtBranchId;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtTitle;

    BookBo bookBo = new BookBoImpl();
    public void initialize(){
        populateCmbBox();
    }

    public void oKBtnOnAction(javafx.event.ActionEvent actionEvent) {
        String id = cbBookId.getValue();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        String branchId = txtBranchId.getText();

        Branch branch1  = new Branch();
        branch1.setId(branchId);
        var dto = new BookDto(id,title,author,genre,branch1);
        boolean flag = bookBo.updateBook(dto);
        boolean isDeleted = bookBo.deleteBook(dto);

        if (flag) {
            new Alert(Alert.AlertType.CONFIRMATION, "Book Updated!").show();
            clearFields();
        }

        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Book Deleted!").show();
            clearFields();
        }

    }

    private void clearFields() {

        txtTitle.setText("");
        txtAuthor.setText("");
        txtGenre.setText("");
        txtBranchId.setText("");

    }

    public void populateCmbBox(){
        ArrayList<String> id =  bookBo.getCmbBookId();
        ObservableList<String> strings = FXCollections.observableArrayList(id);
        cbBookId.setItems(strings);
    }


    public void cmbBookIdOnAction(javafx.event.ActionEvent actionEvent) {
        String id = cbBookId.getValue();
        BookDto bookDto = bookBo.searchBookId(id);
        txtTitle.setText(bookDto.getTitle());
        txtAuthor.setText(bookDto.getAuthor());
        txtGenre.setText(bookDto.getGenre());
        txtBranchId.setText(bookDto.getBranch().getId());
    }


}
