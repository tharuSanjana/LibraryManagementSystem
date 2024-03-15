package org.example.dto.tm;

import org.example.dto.BorrowDto;
import org.example.entity.Book;
import org.example.entity.User;

import java.time.LocalDate;

public class TransactionTm{

    private String id;
    private LocalDate returnDate;
    private LocalDate reserveDate;
    private LocalDate borrowDate;
    private String isReturn;
    private String userId;
    private String userName;

    public TransactionTm(String id, LocalDate returnDate, LocalDate reserveDate, LocalDate borrowDate, String isReturn, String userId, String userName, String bookId, String bookName) {
        this.id = id;
        this.returnDate = returnDate;
        this.reserveDate = reserveDate;
        this.borrowDate = borrowDate;
        this.isReturn = isReturn;
        this.userId = userId;
        this.userName = userName;
        this.bookId = bookId;
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "TransactionTm{" +
                "id='" + id + '\'' +
                ", returnDate=" + returnDate +
                ", reserveDate=" + reserveDate +
                ", borrowDate=" + borrowDate +
                ", isReturn='" + isReturn + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String bookId;
    private String bookName;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public TransactionTm(String id, LocalDate returnDate, LocalDate reserveDate, LocalDate borrowDate, String isReturn, String userId, String bookId, String bookName) {
        this.id = id;
        this.returnDate = returnDate;
        this.reserveDate = reserveDate;
        this.borrowDate = borrowDate;
        this.isReturn = isReturn;
        this.userId = userId;
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public TransactionTm(){}

    public TransactionTm(String id, String bookId, String userId, LocalDate borrowDate, LocalDate returnDate, LocalDate reserveDate, String isReturn) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.reserveDate = reserveDate;
        this.isReturn = isReturn;

    }

   /* public TransactionTm(String id, String bookId, String userId, LocalDate borrowDate, LocalDate returnDate, LocalDate reserveDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.reserveDate = reserveDate;


    }*/

    public TransactionTm(String id, String bookId, String userId, String userName, LocalDate borrowDate, LocalDate returnDate, LocalDate reserveDate) {
        this.id = id;
        this.returnDate = returnDate;
        this.reserveDate = reserveDate;
        this.borrowDate = borrowDate;
        this.userId = userId;
        this.userName = userName;
        this.bookId = bookId;
    }

}
