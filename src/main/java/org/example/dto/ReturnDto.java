package org.example.dto;

import java.time.LocalDate;

public class ReturnDto {
    private String userId;
    private String bookId;
    private String bookName;
    private String availability;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String isReturn;
    private LocalDate reserveDate;

    @Override
    public String toString() {
        return "ReturnDto{" +
                "userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", availability='" + availability + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", isReturn='" + isReturn + '\'' +
                ", reserveDate='" + reserveDate + '\'' +
                '}';
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
    }

    public ReturnDto(String userId, String bookId, String bookName, String availability, LocalDate borrowDate, LocalDate returnDate, String isReturn, LocalDate reserveDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.availability = availability;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.isReturn = isReturn;
        this.reserveDate = reserveDate;
    }
    public ReturnDto(){}
}
