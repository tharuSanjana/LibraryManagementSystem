package org.example.dto;

import java.time.LocalDate;

public class BorrowDto {
    private String userId;
    private String username;
    private String bookId;
    private String bookName;
    private String availability;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String author;
    private String genre;
    private String branchId;
    private String isReturn;
    private String transId;
    private LocalDate reserveDate;

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
    }

    @Override
    public String toString() {
        return "BorrowDto{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", availability='" + availability + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", branchId='" + branchId + '\'' +
                ", isReturn='" + isReturn + '\'' +
                ", transId='" + transId + '\'' +
                ", reserveDate=" + reserveDate +
                '}';
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }
/* public BorrowDto(String userId, String username,String bookId, String bookName, String availability, LocalDate currentDate, LocalDate returnDate) {
        this.userId = userId;
        this.username = username;

        this.bookId = bookId;
        this.bookName = bookName;
        this.availability = availability;
        this.borrowDate = currentDate;
        this.returnDate = returnDate;
    }
*/


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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




    public BorrowDto(String userId, String username, String bookId, String bookName, String availability, LocalDate currentDate, LocalDate returnDate){
        this.userId = userId;
        this.username = username;
        this.bookId = bookId;
        this.bookName = bookName;
        this.availability = availability;
        this.returnDate = returnDate;

    }

   /* public BorrowDto(String userId, String username, String bookId, String bookName, String availability, LocalDate borrowDate, LocalDate returnDate, String author, String genre, String branchId) {
        this.userId = userId;
        this.username = username;
        this.bookId = bookId;
        this.bookName = bookName;
        this.availability = availability;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.author = author;
        this.genre = genre;
        this.branchId = branchId;
    }

    public BorrowDto(String bookId, String bookName, LocalDate borrowDate, LocalDate returnDate){
        this.bookId = bookId;
        this.bookName = bookName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }



    public BorrowDto(String bookId, String title, String author, String branchId, String genre, LocalDate borrowDate, LocalDate returnDate) {
        this.bookId = bookId;
        this.bookName = title;
        this.author = author;
        this.genre = genre;
        this.branchId = branchId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }


    public BorrowDto(String userId, String username, String bookId, String bookName, String availability, LocalDate currentDate, LocalDate returnDate) {
        this.userId = userId;
        this.username = username;
        this.bookId = bookId;
        this.bookName = bookName;
        this.availability = availability;
        this.borrowDate = currentDate;
        this.returnDate = returnDate;
    }*/

    public BorrowDto(String userId, String username, String bookId, String bookName, String availability, LocalDate borrowDate, LocalDate returnDate, String author, String genre, String branchId, String isReturn, String transId, LocalDate reserveDate) {
        this.userId = userId;
        this.username = username;
        this.bookId = bookId;
        this.bookName = bookName;
        this.availability = availability;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.author = author;
        this.genre = genre;
        this.branchId = branchId;
        this.isReturn = isReturn;
        this.transId = transId;
        this.reserveDate = reserveDate;
    }

    public BorrowDto(){}
    public BorrowDto(String bookId, String bookName, LocalDate borrowDate, LocalDate returnDate){
        this.bookId = bookId;
        this.bookName = bookName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowDto(String transId,String userId, String username, String bookId, String bookName, String availability, LocalDate borrowDate, LocalDate returnDate, String isReturn,LocalDate reserveDate) {
        this.transId = transId;
        this.userId = userId;
        this.username = username;
        this.bookId = bookId;
        this.bookName = bookName;
        this.availability = availability;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.isReturn = isReturn;
        this.reserveDate = reserveDate;
    }
}
