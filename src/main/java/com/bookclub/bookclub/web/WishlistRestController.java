package com.bookclub.bookclub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookclub.bookclub.service.impl.MongoWishlistDao;

import ch.qos.logback.core.joran.spi.HttpUtil.RequestMethod;

import com.bookclub.bookclub.model.WishlistItem;
import com.bookclub.bookclub.service.dao.WishlistDao;

@RestController
@RequestMapping(path = "api/wishlist", produces = "application/json")
@CrossOrigin(origins = "*")
public class WishlistRestController {
	private WishlistDao wishlistDao = new MongoWishlistDao();
	
	@Autowired
	public void setWishlistDao(WishlistDao wishlistDao)
	{
		this.wishlistDao = wishlistDao;
	}
	
	@GetMapping()
	public List<WishlistItem> showWishlist(){
		return wishlistDao.list();
	}
	
	@GetMapping(path = "/{id}")
	public WishlistItem findById(@PathVariable String id)
	{
		return wishlistDao.find(id);
		 
	}
}
