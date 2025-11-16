package com.bookclub.bookclub.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookTest {

    @Test
    void gettersAndToString_workAsExpected() {
        Book book = new Book("ISBN:12345", "Title", "Desc", "http://info", 200);
        assertEquals("ISBN:12345", book.getIsbn());
        assertEquals("Title", book.getTitle());
        assertEquals("Desc", book.getDescription());
        assertEquals("http://info", book.getInfoUrl());
        assertEquals(200, book.getNumOfPages());
        String s = book.toString();
        assertTrue(s.contains("Title"));
        assertTrue(s.contains("ISBN:12345"));
    }
}
