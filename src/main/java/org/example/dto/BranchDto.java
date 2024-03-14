package org.example.dto;

public class BranchDto {
    private String id;
    private String location;

    @Override
    public String toString() {
        return "BranchDto{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BranchDto(String id, String location) {
        this.id = id;
        this.location = location;
    }
    public BranchDto(){}

    public BranchDto(String location) {

        this.location = location;
    }
}
