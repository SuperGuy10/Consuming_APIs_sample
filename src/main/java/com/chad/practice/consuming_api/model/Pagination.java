package com.chad.practice.consuming_api.model;

import lombok.Data;

@Data
public class Pagination {
    private int total;
    private int pages;
    private int page;
    private int limit;
}
