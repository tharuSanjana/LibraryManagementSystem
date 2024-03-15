package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.bo.BookBo;
import org.example.bo.BookBoImpl;
import org.example.dto.BookDto;
import org.example.dto.BorrowDto;
import org.example.dto.GetUserIdDto;
import org.example.dto.SearchBookDto;
import org.example.dto.tm.BookTm;
import org.example.entity.Branch;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class UserBooksFormController {

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
    private AnchorPane root;

    @FXML
    private TableView<BookTm> tblBookList;

    @FXML
    private ComboBox<String> cmbBookId;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtSearchBook;

    BookBo bookBo = new BookBoImpl();
    private ObservableList<BookTm> obList = FXCollections.observableArrayList();
    public void initialize(){
        setCellValueFactory();
        loadAvailableBooks();
        //setUserIdAndName(password);
        populateCmbBox();
        setUserIdName();
    }
    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/userDashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.centerOnScreen();

    }

    public void searchBtnOnAction(ActionEvent actionEvent) throws IOException {
        String bookName = txtSearchBook.getText();
        /*BookDto bookDto = bookBo.searchBookName(bookName);
        System.out.println(bookDto);*/
       /* System.out.println(bookName);
        UserSearchBookFormController userSearchBookFormController = new UserSearchBookFormController();
        userSearchBookFormController.searchBookDetails(bookName);
        System.out.println(bookName);*/
        SearchBookDto searchBook = new SearchBookDto();
        searchBook.setBookName(bookName);

        URL resource = this.getClass().getResource("/view/userSearchBookForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Search Book");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();

    }

    public void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branchId"));
    }

    public void loadAvailableBooks(){
        List<BookDto> bookList = null;

        bookList = bookBo.getAvailableBooks();
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
        tblBookList.setItems(obList);
    }

    public void populateCmbBox(){
        ArrayList<String> id =  bookBo.getCmbBookId();
        ObservableList<String> strings = FXCollections.observableArrayList(id);
        cmbBookId.setItems(strings);
    }



    public void txtUserOnAction(ActionEvent actionEvent) {

    }
    @FXML
    void cmbBookIdOnAction(ActionEvent event) {
        String id = cmbBookId.getValue();
        BookDto bookDto = bookBo.searchBookId(id);
        txtBookName.setText(bookDto.getTitle());
    }

    @FXML
    void borrowBtnOnActon(ActionEvent event) {
        String userId = txtUserId.getText();
        String username = txtUsername.getText();
        String bookId = cmbBookId.getValue();
        String bookName = txtBookName.getText();
        String availability = "no";
        LocalDate currentDate = LocalDate.now();
        //LocalDate currentDate = LocalDate.parse("2024-03-01");
        LocalDate returnDate = currentDate.plusDays(8);
       // LocalDate returnDate = LocalDate.parse("2024-03-09");
        String isReturn = "no";
        LocalDate reserveDate = null;

        UserTransactionFormController userTransactionFormController = new UserTransactionFormController();
        String s = userTransactionFormController.generateTransactionId();
        var dto = new BorrowDto(s,userId,username,bookId,bookName,availability,currentDate,returnDate,isReturn,reserveDate);
        boolean isBorrow = bookBo.borrowBook(dto);

        if (isBorrow){
            new Alert(Alert.AlertType.CONFIRMATION, "Borrow Success!").show();
        }

    }
    public void setUserIdName(){
        GetUserIdDto getUserIdDto = new GetUserIdDto();
        String id = getUserIdDto.getId();
        txtUserId.setText(id);

        String name = bookBo.getUsername(id);
        txtUsername.setText(name);
    }


}
