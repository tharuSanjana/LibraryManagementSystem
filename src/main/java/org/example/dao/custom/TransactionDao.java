package org.example.dao.custom;

import org.example.entity.UserBook;

import java.time.LocalDate;
import java.util.ArrayList;

public interface TransactionDao {
    String generateTransId();

    boolean saveUserBook(String transId, String isReturn, LocalDate reserveDate, LocalDate borrowDate, LocalDate returnDate, String userId, String bookId);

    ArrayList<UserBook> getAllUserBorrowBooks();

    boolean saveReturnUserBook(String isReturn, LocalDate reserveDate, String bookId);
}
