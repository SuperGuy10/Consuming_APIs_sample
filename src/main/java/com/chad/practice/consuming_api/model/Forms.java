package com.chad.practice.consuming_api.model;

import lombok.Data;

@Data
public class Forms {
    private String code;
    private Meta meta;
    private User[] data;
}
