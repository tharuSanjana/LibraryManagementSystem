package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.bo.BookBo;
import org.example.bo.BookBoImpl;
import org.example.dto.BookDto;
import org.example.dto.SearchBookDto;
import org.example.dto.tm.BookTm;
import org.example.entity.Branch;

import java.util.List;

public class UserSearchBookFormController {
    @FXML
    private Button cancelBtn;
    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<BookTm> tblSearchBook;

    BookBo bookBo = new BookBoImpl();
    private ObservableList<BookTm> obList = FXCollections.observableArrayList();

    public void initialize(String bookName){
        setCellValueFactory();
        searchBookDetails();
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

    public void setCellValueFactory(){
       colId.setCellValueFactory(new PropertyValueFactory<>("id"));
       colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
       colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
       colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
      // colBranch.setCellValueFactory(new PropertyValueFactory<>("branchId"));
    }

   /* public void  loadAllSearchBook(String bookName){

        List<BookDto> bookList = null;

        bookList = bookBo.searchBookName(bookName);
        for (BookDto bookDto:bookList) {
            Branch branch = new Branch();
            String branchId = branch.setId(bookDto.getBranch().getId());

            obList.add(new BookTm(bookDto.getId(),
                            bookDto.getTitle(),
                            bookDto.getAuthor(),
                            bookDto.getGenre(),
                            branchId
                    )
            );

        }
        tblSearchBook.setItems(obList);
    }*/

    public void searchBookDetails(){
        SearchBookDto searchBookDto = new SearchBookDto();
        String bookName = searchBookDto.getBookName();

        List<BookDto> bookList = null;

        bookList = bookBo.searchBookName(bookName);
        for (BookDto bookDto:bookList) {
           /* Branch branch = new Branch();
            String branchId = branch.setId(bookDto.getBranch().getId());*/

            obList.add(new BookTm(bookDto.getId(),
                            bookDto.getTitle(),
                            bookDto.getAuthor(),
                            bookDto.getGenre()
                           // branchId
                    )
            );

        }
        tblSearchBook.setItems(obList);
    }
}
