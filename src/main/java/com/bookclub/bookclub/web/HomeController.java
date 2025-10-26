package com.bookclub.bookclub.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;                 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookclub.bookclub.model.Book;
import com.bookclub.bookclub.service.impl.RestBookDao;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome(Model model) {
    	 // Create a new instance of MemBookDao
    	RestBookDao booksDao = new RestBookDao();

        // Fetch the list of books
        List<Book> books = booksDao.list();

        // Add the books list to the model with key "books"
        model.addAttribute("books", books);

        // Return index.html view
        return "index";
    }

    @GetMapping("/about")
    public String showAboutUs(Model model) {
        return "about";      // templates/about.html
    }

    @GetMapping("/contact")
    public String showContactUs(Model model) {
        return "contact";    // templates/contact.html
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getMonthlyBook(@PathVariable("id") String id, Model model) {
        // Create a new instance of MemBookDao
        RestBookDao booksDao = new RestBookDao();

        // Find the book by ISBN
        Book book = booksDao.find(id);

        // Add the book to the model
        model.addAttribute("book", book);

        // Return the view (templates/monthly-books/view.html)
        return "monthly-books/view";
    }
}
