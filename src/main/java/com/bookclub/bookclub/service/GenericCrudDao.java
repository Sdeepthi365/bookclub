package com.bookclub.bookclub.service;

import java.util.List;

public interface GenericCrudDao<T, K> {
    void add(T entity);
    void update(T entity);
    boolean remove(K key); 
    List<T> list(K key); 
    T find(K key);
    
    
}
