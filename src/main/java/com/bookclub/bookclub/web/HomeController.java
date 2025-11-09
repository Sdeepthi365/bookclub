package com.bookclub.bookclub.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;                 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookclub.bookclub.model.Book;
import com.bookclub.bookclub.model.BookOfTheMonth;
import com.bookclub.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.bookclub.service.impl.RestBookDao;

@Controller
public class HomeController {

	private BookOfTheMonthDao bookOfTheMonthDao;
	
	 @Autowired
	    public void setBookOfTheMonthDao(BookOfTheMonthDao bookOfTheMonthDao) {
	        this.bookOfTheMonthDao = bookOfTheMonthDao;
	    }
	 
    @GetMapping("/")
    public String showHome(Model model) {
    	int currentMonth = LocalDate.now().getMonthValue();
        System.out.println("Current Month: " + currentMonth);
        List<BookOfTheMonth> monthlyBooks = bookOfTheMonthDao.list(String.valueOf(currentMonth));
        StringBuilder isbnBuilder = new StringBuilder();
        isbnBuilder.append("ISBN:");
        
        for (BookOfTheMonth bom : monthlyBooks) {
        	isbnBuilder.append(bom.getIsbn()).append(",");
        }
        String isbnString = isbnBuilder.toString().substring(0, isbnBuilder.toString().length()-1);
    	RestBookDao booksDao = new RestBookDao();

        List<Book> books = booksDao.list(isbnString);

        model.addAttribute("books", books);
        model.addAttribute("month", currentMonth);
        
        return "index";
    }

    @GetMapping("/about")
    public String showAboutUs(Model model) {
        return "about";      
    }

    @GetMapping("/contact")
    public String showContactUs(Model model) {
        return "contact";    
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getMonthlyBook(@PathVariable("id") String id, Model model) {
       
        RestBookDao booksDao = new RestBookDao();

        Book book = booksDao.find(id);

        model.addAttribute("book", book);

        return "monthly-books/view";
    }
}
