package com.bookclub.bookclub.model;

import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotEmpty;

public class BookOfTheMonth {

    @Id
    private String id;
    private int month;

    @NotEmpty(message = "ISBN is a required field.")
    private String isbn;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    @Override
    public String toString() {
        return "BookOfTheMonth [id=" + id + ", month=" + month + ", isbn=" + isbn + "]";
    }
}
