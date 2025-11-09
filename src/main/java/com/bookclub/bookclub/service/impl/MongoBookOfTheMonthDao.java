package com.bookclub.bookclub.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.bookclub.bookclub.model.BookOfTheMonth;
import com.bookclub.bookclub.service.dao.BookOfTheMonthDao;

@Repository("bookOfTheMonthDao")
public class MongoBookOfTheMonthDao implements BookOfTheMonthDao {

	@Autowired
	    private MongoTemplate mongoTemplate;
	
	@Override
	public List<BookOfTheMonth> list(String key){
		int month = Integer.parseInt(key);
		System.out.println("Month:" + month);
		if(month == 999) {
			System.out.println("Books in DB: " + mongoTemplate.findAll(BookOfTheMonth.class));
			return mongoTemplate.findAll(BookOfTheMonth.class);
		}
		
		Query query = new Query();
		
		query.addCriteria(Criteria.where("month").is(month));
		System.out.println("Books in DB: " + mongoTemplate.findAll(BookOfTheMonth.class));
		return mongoTemplate.find(query, BookOfTheMonth.class);
	}

	@Override
	public void add(BookOfTheMonth entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BookOfTheMonth entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean remove(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BookOfTheMonth find(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
