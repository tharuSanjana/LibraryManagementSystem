package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.bo.BookBo;
import org.example.bo.BookBoImpl;
import org.example.dto.BookDto;
import org.example.entity.Branch;

import java.util.ArrayList;

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

    BookBo bookBo = (BookBo) new BookBoImpl();

    public void initialize(){
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
        String id = lblBookId.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        String branchId = cmbBranchId.getValue();
        String availability = "yes";

        Branch branch = new Branch();
        branch.setId(branchId);
        var dto = new BookDto(id,title,author,genre,availability,branch);

        boolean flag = bookBo.save(dto);

        if(flag){
            new Alert(Alert.AlertType.CONFIRMATION, "Book saved!").show();
            clearFields();
        }


    }

    private void clearFields() {
        lblBookId.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtGenre.setText("");

    }
    private String generateBookId(){
        String bookId = null;
        bookId = String.valueOf(bookBo.getGenerateBookId());
        lblBookId.setText(bookId);

        return bookId;
    }



    public void populateCmbBox(){
        ArrayList<String> id =  bookBo.getCmbBranchId();
        ObservableList<String> strings = FXCollections.observableArrayList(id);
        cmbBranchId.setItems(strings);
    }
}
