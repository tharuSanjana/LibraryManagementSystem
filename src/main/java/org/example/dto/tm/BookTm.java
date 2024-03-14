package org.example.dto.tm;

import java.time.LocalDate;

public class BookTm {
    private String id;
    private String title;
    private String author;
    private String genre;
    private String availability;
    private String branchId;



    private  LocalDate borrowDate;
    private LocalDate returnDate;

    @Override
    public String toString() {
        return "BookTm{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", availability='" + availability + '\'' +
                ", branchId='" + branchId + '\'' +

                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +

                '}';
    }

    public BookTm(String id, String title, String author, String genre, String availability, String branchId, LocalDate borrowDate, LocalDate returnDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.branchId = branchId;

        this.borrowDate = borrowDate;
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }



    public BookTm(String id, String title, String author, String genre, String availability, String branchId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.branchId = branchId;


    }



    public BookTm(){}

    public BookTm(String id, String title, String author, String genre, String branchId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.branchId = branchId;
    }

    public BookTm(String id, String title, LocalDate borrowDate, LocalDate returnDate) {
        this.id = id;
        this.title = title;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;

    }
}
