package org.example.dao;

import org.example.dto.BookDto;
import org.example.entity.Book;

import java.time.LocalDate;
import java.util.ArrayList;

public interface BookDao {
    String getGenerateBookId();
    ArrayList<String> getCmbBranchId();
    boolean save(Book book);
    ArrayList<Book> getAllBooks();
    boolean deleteBook(Book book );
    ArrayList<Book> getAvailableBooks();

  ArrayList<String> getCmbBookId();

    BookDto searchBookId(String id);

    boolean saveBorrowBook(String bookId, String bookName, String availabiity, LocalDate currentDate, LocalDate returnDate);
    //boolean saveBorrowBookUser(String userId, String bookId);

    boolean updateBook(Book book);

    ArrayList<String> getUserCmbBookId(String userId, String isReturn);

    boolean saveReturnBook(String availability, LocalDate borrowDate, LocalDate returnDate, String bookId);

    boolean deleteBookBranch(String id);

    String getUsername(String id);
    //ArrayList<Book> searchBookName(String bookName);
}
