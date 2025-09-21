package com.bookclub.bookclub.service.impl;

import com.bookclub.bookclub.model.Book;
import com.bookclub.bookclub.service.dao.BookDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemBookDao implements BookDao {

    private List<Book> books;

    // Constructor: initialize with 5 sample books
    public MemBookDao() {
        books = new ArrayList<>();

        books.add(new Book("1111", "Java Basics", "Introductory Java programming book", 200,
                Arrays.asList("John Doe", "Jane Doe")));
        books.add(new Book("2222", "Spring Boot Guide", "Comprehensive Spring Boot reference", 350,
                Arrays.asList("Alice Smith")));
        books.add(new Book("3333", "Database Design", "Concepts of relational databases", 280,
                Arrays.asList("Robert Brown")));
        books.add(new Book("4444", "Web Development", "Frontend and backend fundamentals", 400,
                Arrays.asList("Emma Johnson", "Chris Martin")));
        books.add(new Book("5555", "Microservices Architecture", "Patterns and best practices", 320,
                Arrays.asList("Sophia Lee")));
    }

    // Override list() to return the books
    @Override
    public List<Book> list() {
        return books;
    }

    // Override find() using a loop to match ISBN
    @Override
    public Book find(String key) {
        for (Book book : books) {
            if (book.getIsbn().equals(key)) {
                return book;
            }
        }
        return null; // if no match is found
    }

}
