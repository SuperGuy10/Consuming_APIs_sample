package com.chad.practice.consuming_api.model;
import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;
    private String created_at;
    private String updated_at;


}
