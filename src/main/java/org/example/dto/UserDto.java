package org.example.dto;

import org.example.entity.Branch;

public class UserDto {
    private String id;
    private String name;
    private String email;
    private Branch branch;
    private String username;
    private String password;

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", branch='" + branch + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
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

    public UserDto(String id, String name, String email,Branch branch, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.branch = branch;
        this.username = username;
        this.password = password;
    }
    public UserDto(){}

    public UserDto(String name, String email, String username, String password,Branch branch) {

        this.name = name;
        this.email = email;
        this.branch = branch;
        this.username = username;
        this.password = password;
    }


    public UserDto(String id,String name, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserDto(String name, String email,Branch branch) {
        this.name = name;
        this.email = email;
        this.branch = branch;
    }

    public UserDto(String id, String name, String email,Branch branch) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.branch = branch;

    }
}
