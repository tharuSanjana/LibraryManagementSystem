package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
@Entity
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String genre;
    private String availability;
    private LocalDate borrowDate;
    private  LocalDate returnDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branchId")
    private Branch branches;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<UserBook> userBooks;

    public Book(String id, String title, String author, String genre, String availability, LocalDate borrowDate, LocalDate returnDate, Branch branches, List<UserBook> userBooks) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.branches = branches;
        this.userBooks = userBooks;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", availability='" + availability + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", branches=" + branches +
                ", userBooks=" + userBooks +
                '}';
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

    public List<UserBook> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(List<UserBook> userBooks) {
        this.userBooks = userBooks;
    }

    public Book(String id) {
        this.id = id;

    }



    public Branch getBranches() {
        return branches;
    }

    public void setBranches(Branch branches) {
        this.branches = branches;
    }

    public Book(String id, String title, String author, String genre, String availability, Branch branch) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.branches = branch;
    }

    public Book(String id, String title, String author, String genre, Branch branch) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.branches = branch;
    }



    public Book(){}
}
