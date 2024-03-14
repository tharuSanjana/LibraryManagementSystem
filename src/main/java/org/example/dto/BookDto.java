package org.example.dto;

import org.example.entity.Branch;

import java.time.LocalDate;

public class BookDto {
    private String id;
    private String title;
    private String author;
    private String genre;
    private String availability;
    private Branch branch;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    @Override
    public String toString() {
        return "BookDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", availability='" + availability + '\'' +
                ", branch=" + branch +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public BookDto(String id, String title, String author, String genre, String availability, Branch branch) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.branch = branch;
    }
    public BookDto(String bookId, String bookName, String availability, LocalDate currentDate, LocalDate returnDate){
        this.id = bookId;
        this.title = bookName;
        this.availability = availability;
        this.borrowDate = currentDate;
        this.returnDate = returnDate;
    }
    public BookDto(String id, String title, String author, String genre, Branch branch) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.branch = branch;
    }

    public BookDto(String title,String author,String genre,Branch branch) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.branch = branch;
    }

  /*  public BookDto(String bookId, String title, String author, String genre,Branch branch){
        this.id = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.branch = branch;
    }*/

    }

