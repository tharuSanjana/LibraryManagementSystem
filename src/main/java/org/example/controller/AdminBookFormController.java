package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.BoFactory;
import org.example.bo.custom.BookBo;
import org.example.bo.custom.BranchBo;
import org.example.bo.impl.BookBoImpl;
import org.example.dto.BookDto;
import org.example.dto.tm.BookTm;
import org.example.entity.Branch;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class AdminBookFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colAvailable;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableView<BookTm> tblBooks;

    private ObservableList<BookTm> obList = FXCollections.observableArrayList();


   // BookBo bookBo = new BookBoImpl();
    BookBo bookBo = (BookBo) BoFactory.boFactory().getBoTypes(BoFactory.BOTypes.BOOK);

    public void initialize(){
        setCellValueFactory();
        loadAllBooks();
    }
    @FXML
    void addBookBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/adminAddBookForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Book");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
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

    public void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colAvailable.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branchId"));
    }

    public void loadAllBooks(){
        List<BookDto> bookList = null;

        bookList = bookBo.getAllBooks();
        for (BookDto bookDto:bookList) {
            Branch branch = new Branch();
            String branchId = branch.setId(bookDto.getBranch().getId());

            obList.add(new BookTm(bookDto.getId(),
                    bookDto.getTitle(),
                    bookDto.getAuthor(),
                    bookDto.getGenre(),
                    bookDto.getAvailability(),
                    branchId
                    )
            );


        }
        tblBooks.setItems(obList);
    }



    @FXML
    void deleteBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/adminUpdateBook.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Delete Book");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/adminUpdateBook.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Update Book");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

}
