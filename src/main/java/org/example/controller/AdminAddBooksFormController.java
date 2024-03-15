package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.BoFactory;
import org.example.bo.custom.BookBo;
import org.example.bo.impl.BookBoImpl;
import org.example.dto.BookDto;
import org.example.entity.Branch;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AdminAddBooksFormController {
    @FXML
    private Button cancelBtn;
    @FXML
    private ComboBox<String> cmbBranchId;

    @FXML
    private Label lblBookId;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtTitle;
    //BookBo bookBo = (BookBo) new BookBoImpl();
    BookBo bookBo = (BookBo) BoFactory.boFactory().getBoTypes(BoFactory.BOTypes.BOOK);

    public void initialize() {
        populateCmbBox();
        generateBookId();
    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

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
        boolean isValid = validBooks();
        if (isValid) {
            String id = lblBookId.getText();
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String genre = txtGenre.getText();
            String branchId = cmbBranchId.getValue();
            String availability = "yes";

            Branch branch = new Branch();
            branch.setId(branchId);
            var dto = new BookDto(id, title, author, genre, availability, branch);

            boolean flag = bookBo.save(dto);

            if (flag) {
                new Alert(Alert.AlertType.CONFIRMATION, "Book saved!").show();
                clearFields();
            }
        }


    }

    private void clearFields() {
        lblBookId.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtGenre.setText("");

    }

    private String generateBookId() {
        String bookId = null;
        bookId = String.valueOf(bookBo.getGenerateBookId());
        lblBookId.setText(bookId);

        return bookId;
    }


    public void populateCmbBox() {
        ArrayList<String> id = bookBo.getCmbBranchId();
        ObservableList<String> strings = FXCollections.observableArrayList(id);
        cmbBranchId.setItems(strings);
    }

    private boolean validBooks() {
        String id = lblBookId.getText();
        boolean isIdValid = Pattern.matches("[B][0-9]{3,}", id);
        if (!isIdValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid book id").show();
            return false;
        }
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
        String genre = txtAuthor.getText();
        boolean isGenreValid = Pattern.matches("[A-Za-z]{4,}",genre);
        if (!isGenreValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid genre").show();
            return false;
        }


        return true;
    }

}
