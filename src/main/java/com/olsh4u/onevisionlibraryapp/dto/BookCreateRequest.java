package com.olsh4u.onevisionlibraryapp.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class BookCreateRequest {
    @NotNull(message = "title is required")
    @NotEmpty(message = "title should not be empty")
    private String title;
    @NotNull(message = "author is required")
    @NotEmpty(message = "author should not be null")
    private String author;
    private String description;

    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }


    public String getDescription() {
        return description;
    }

}
