package org.example.bo.custom;

import org.example.SuperBO;
import org.example.dto.BookDto;
import org.example.dto.BorrowDto;

import java.util.ArrayList;

public interface BookBo extends SuperBO {
    String getGenerateBookId();
     ArrayList<String> getCmbBranchId();
     boolean save(BookDto dto);
     ArrayList<BookDto> getAllBooks();
     boolean deleteBook(BookDto dto);
    ArrayList<BookDto> getAvailableBooks();
    ArrayList<String> getCmbBookId();
    BookDto searchBookId(String id);
    boolean borrowBook(BorrowDto dto);

    boolean updateBook(BookDto dto);

    ArrayList<String> getUserCmbBookId(String userId, String isReturn);

    String getUsername(String id);

     ArrayList<BookDto> searchBookName(String bookName);
}
