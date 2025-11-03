package com.bookclub.bookclub.service;

import java.util.List;

public interface GenericCrudDao<T, K> {
    void add(T entity);
    List<T> list(K key); // Updated
    T find(K key);
    void update(T entity);
    boolean remove(K key); // Updated
}
