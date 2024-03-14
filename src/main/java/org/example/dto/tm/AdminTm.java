package org.example.dto.tm;

public class AdminTm {
    private String id;
    private String name;
    private String email;
    private String branchId;

    @Override
    public String toString() {
        return "AdminTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", branchTd='" + branchId + '\'' +
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public AdminTm(String id, String name, String email, String branchId, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.branchId = branchId;
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;

    public AdminTm(){}
}
