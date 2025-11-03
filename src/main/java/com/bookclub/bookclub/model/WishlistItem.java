package com.bookclub.bookclub.model;


import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;


public class WishlistItem {
	
	@Id
    private String id;
	
	@NotEmpty(message = "ISBN is a required field.")
    private String isbn;

    @NotEmpty(message = "Title is a required field.")
    private String title;
    
    private String username;
	
	public WishlistItem()
	{
		
	}
	
	public WishlistItem(String isbn, String title, String username)
	{
		this.isbn = isbn;
        this.title = title;
        this.username = username;
	}
	
	  // Getters and Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getId() {
    	return id;
    }
    public void setId(String id) {
    	this.id = id;
    }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    // toString method
    @Override
    public String toString() {
        return "WishlistItem [id=" + id + ", isbn=" + isbn + ", title=" + title +
               ", username=" + username + "]";
    }

}
