package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
@Entity
public class UserBook {

    @Id
    private String id;
    private LocalDate returnDate;
    private LocalDate reserveDate;
    private LocalDate borrowDate;
    private String isReturn;

    @Override
    public String toString() {
        return "UserBook{" +
                "id='" + id + '\'' +
                ", returnDate=" + returnDate +
                ", reserveDate=" + reserveDate +
                ", borrowDate=" + borrowDate +
                ", isReturn='" + isReturn + '\'' +
                ", user=" + user +
                ", book=" + book +
                '}';
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

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

    public UserBook(String id, LocalDate returnDate, LocalDate reserveDate, LocalDate borrowDate, String isReturn, User user, Book book) {
        this.id = id;
        this.returnDate = returnDate;
        this.reserveDate = reserveDate;
        this.borrowDate = borrowDate;
        this.isReturn = isReturn;
        this.user = user;
        this.book = book;
    }

    public UserBook(){}
}
