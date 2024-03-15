package org.example.dto;

import org.example.entity.Book;
import org.example.entity.User;

import java.time.LocalDate;

public class TransactionDto {
    private String id;
    private LocalDate returnDate;
    private LocalDate reserveDate;
    private LocalDate borrowDate;
    private String isReturn;
    private User user;
    private Book book;

    @Override
    public String toString() {
        return "TransactionDto{" +
                "id='" + id + '\'' +
                ", returnDate=" + returnDate +
                ", reserveDate=" + reserveDate +
                ", borrowDate=" + borrowDate +
                ", isReturn='" + isReturn + '\'' +
                ", user=" + user +
                ", book=" + book +
                '}';
    }


    public TransactionDto(String id, LocalDate returnDate, LocalDate reserveDate, LocalDate borrowDate, String isReturn, User user, Book book) {
        this.id = id;
        this.returnDate = returnDate;
        this.reserveDate = reserveDate;
        this.borrowDate = borrowDate;
        this.isReturn = isReturn;
        this.user = user;
        this.book = book;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

   /* public TransactionDto(String bookId, String bookName, LocalDate borrowDate, LocalDate returnDate) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }*/

   public TransactionDto(){}

    public TransactionDto(String id, Book book , User user, LocalDate borrowDate,LocalDate returnDate, LocalDate reserveDate, String isReturn) {
        this.id = id;
        this.returnDate = returnDate;
        this.reserveDate = reserveDate;
        this.borrowDate = borrowDate;
        this.isReturn = isReturn;
        this.user = user;
        this.book = book;
    }

    public TransactionDto( User user, String isReturn) {

        this.isReturn = isReturn;
        this.user = user;

    }

    public TransactionDto(String id, User user, Book book ,LocalDate returnDate, LocalDate reserveDate , LocalDate borrowDate) {
        this.id = id;
        this.returnDate = returnDate;
        this.reserveDate = reserveDate;
        this.borrowDate = borrowDate;
        this.user = user;
        this.book = book;
    }
}
