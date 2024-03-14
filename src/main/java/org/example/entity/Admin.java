package org.example.entity;

import jakarta.persistence.*;

@Entity
public class Admin {
    @Id
    private String id;
    private String name;
    private String email;
    private String username;
    private String password;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branchId")
    private Branch branches;


    public Branch getBranches() {
        return branches;
    }

    public void setBranches(Branch branches) {
        this.branches = branches;
    }


    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", branches=" + branches +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(String id, String name, String email, String branch, String username, String password, Branch branches) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.branches = branches;
    }

    public Admin(String username, String password){
        this.username = username;
        this.password = password;
    }
    public Admin(){}
    public Admin(String id, String name, String email, Branch branch, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.branches = branch;
        this.username = username;
        this.password = password;
    }
}
