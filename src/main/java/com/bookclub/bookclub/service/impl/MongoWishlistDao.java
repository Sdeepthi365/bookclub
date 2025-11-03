package com.bookclub.bookclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.bookclub.bookclub.model.WishlistItem;
import com.bookclub.bookclub.service.dao.WishlistDao;

@Repository("wishlistDao")
public class MongoWishlistDao implements WishlistDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<WishlistItem> list(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.find(query, WishlistItem.class);
    }
    
    @Override
    public void add(WishlistItem entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public void update(WishlistItem entity) {
        Query query = new Query(Criteria.where("id").is(entity.getId()));
        Update update = new Update()
                .set("isbn", entity.getIsbn())
                .set("title", entity.getTitle())
                .set("username", entity.getUsername());
        mongoTemplate.updateFirst(query, update, WishlistItem.class);
    }

    @Override
    public boolean remove(String key) {
        Query query = new Query(Criteria.where("id").is(key));
        mongoTemplate.remove(query, WishlistItem.class);
        return true;
    }

    @Override
    public WishlistItem find(String key) {
        Query query = new Query(Criteria.where("id").is(key));
        return mongoTemplate.findOne(query, WishlistItem.class);
    }

}
