package org.example.dto;

public class LoginDto {
    private static String id;

    public  String getId() {
        return id;
    }

    public  void setId(String id) {
        LoginDto.id = id;
    }

    public LoginDto() {
    }
}
