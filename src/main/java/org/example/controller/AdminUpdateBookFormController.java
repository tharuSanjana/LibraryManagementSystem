package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.BoFactory;
import org.example.bo.custom.BookBo;
import org.example.bo.impl.BookBoImpl;
import org.example.dto.BookDto;
import org.example.entity.Branch;

import java.util.ArrayList;
import java.util.regex.Pattern;

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

    //BookBo bookBo = new BookBoImpl();
    BookBo bookBo = (BookBo) BoFactory.boFactory().getBoTypes(BoFactory.BOTypes.BOOK);

    public void initialize(){
        populateCmbBox();
    }

    public void oKBtnOnAction(javafx.event.ActionEvent actionEvent) {
        if (validBooks()){
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
    private boolean validBooks() {

        String title = txtTitle.getText();
        boolean isTitleValid = Pattern.matches("[A-Za-z]{4,}", title);
        if (!isTitleValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid book title").show();
            return false;
        }
        String author = txtAuthor.getText();
        boolean isAuthorValid = Pattern.matches("[A-Za-z]{4,}",author);
        if (!isAuthorValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid author").show();
            return false;
        }
        String genre = txtGenre.getText();
        boolean isGenreValid = Pattern.matches("[A-Za-z]{4,}",genre);
        if (!isGenreValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid genre").show();
            return false;
        }


        return true;
    }

}
