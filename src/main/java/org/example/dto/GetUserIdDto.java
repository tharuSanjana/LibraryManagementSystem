package org.example.dto;

public class GetUserIdDto {
    private static String id;

    @Override
    public String toString() {
        return "GetUserIdDto{" +
                "id='" + id + '\'' +
                '}';
    }

    public  String getId() {
        return id;
    }

    public  void setId(String id) {
        GetUserIdDto.id = id;
    }

    public GetUserIdDto(String id) {
        this.id = id;
    }
    public GetUserIdDto(){}
}
