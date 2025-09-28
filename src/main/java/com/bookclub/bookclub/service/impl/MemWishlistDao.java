package com.bookclub.bookclub.service.impl;
import java.util.ArrayList;
import java.util.List;

import com.bookclub.bookclub.model.WishlistItem;
import com.bookclub.bookclub.service.dao.WishlistDao;


public class MemWishlistDao implements WishlistDao{

	private final List<WishlistItem> wishlist;
	
    public MemWishlistDao() {
        wishlist = new ArrayList<>();
        wishlist.add(new WishlistItem("1111", "Java Basics"));
        wishlist.add(new WishlistItem("2222", "Spring Boot Guide"));
        wishlist.add(new WishlistItem("3333", "Database Design"));
        wishlist.add(new WishlistItem("4444", "Web Development"));
        wishlist.add(new WishlistItem("5555", "Microservices Architecture"));
    }
    @Override
    public List<WishlistItem> list() {  
        return this.wishlist;
    }

    @Override
    public WishlistItem find(String isbn) {
        if (isbn == null) return null;
        for (WishlistItem item : wishlist) {
            if (isbn.equalsIgnoreCase(item.getIsbn())) {
                return item;
            }
        }
        return null;
    }
}
