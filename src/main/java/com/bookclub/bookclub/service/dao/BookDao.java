package com.bookclub.bookclub.service.dao;

import com.bookclub.bookclub.model.Book;
import com.bookclub.bookclub.service.GenericDao;

public interface BookDao extends GenericDao<Book, String> {
    // Here Book is the entity type (E), and String (ISBN) is the key (K).
}
