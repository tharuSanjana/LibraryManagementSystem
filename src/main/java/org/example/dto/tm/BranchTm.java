package org.example.dto.tm;

import javafx.scene.control.Button;

public class BranchTm {
    private String id;
    private String location;
    private Button update_button;
    private Button delete_button;


    @Override
    public String toString() {
        return "BranchTm{" +
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

    public BranchTm(String id, String location) {
        this.id = id;
        this.location = location;

    }

    public BranchTm(){}
}
