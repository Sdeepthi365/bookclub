package com.bookclub.bookclub.service;

import java.util.List;

public interface GenericDao<E, K> {

    // Returns a list of all entities of type E
    List<E> list(K key);

    // Finds a specific entity by its key of type K
    E find(K key);
}
