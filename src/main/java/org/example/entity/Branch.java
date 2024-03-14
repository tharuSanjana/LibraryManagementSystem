package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Branch {
    @Id
    private String id;
    private String location;
    @OneToMany(mappedBy = "branches",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Book> books;

    @OneToMany(mappedBy = "branches",cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy = "branches",cascade = CascadeType.ALL)
    private List<Admin> admins;

    public Branch(String id, String location, List<Book> books, List<User> users, List<Admin> admins) {
        this.id = id;
        this.location = location;
        this.books = books;
        this.users = users;
        this.admins = admins;
    }

    @Override
    public String toString() {
       /* return "Branch{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", books=" + books +
                ", users=" + users +
                ", admins=" + admins +
                '}';*/
        return "Branch{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", bookCount=" + (books != null ? books.size() : 0) + // Include book count instead of listing all books
                '}';

    }

    public String getId() {
        return id;
    }

    public String setId(String id) {
        this.id = id;
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public Branch(String id, String location) {
        this.id = id;
        this.location = location;
    }
    public Branch(){}
}
