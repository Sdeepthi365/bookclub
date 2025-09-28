package com.bookclub.bookclub.model;


import jakarta.validation.constraints.NotEmpty;


public class WishlistItem {
	
	@NotEmpty(message = "ISBN is a required field.")
    private String isbn;

    @NotEmpty(message = "Title is a required field.")
    private String title;
	
	public WishlistItem()
	{
		
	}
	
	public WishlistItem(String isbn, String title)
	{
		this.isbn = isbn;
        this.title = title;
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
    
    // toString method
    @Override
    public String toString() {
        return "WishlistItem{isbn=" + isbn + ", title=" + title + "}";
    }

}
