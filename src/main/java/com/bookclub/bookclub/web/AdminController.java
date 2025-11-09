package com.bookclub.bookclub.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.bookclub.bookclub.model.BookOfTheMonth;
import com.bookclub.bookclub.service.dao.BookOfTheMonthDao;

@Controller
@RequestMapping("/monthly-books")
public class AdminController {

    @Autowired
    private BookOfTheMonthDao bookOfTheMonthDao;

	@GetMapping("/list")
	public String showBookOfTheMonth(Model model) {
		model.addAttribute("books", bookOfTheMonthDao.list("999"));
		return "monthly-books/list";
	}
	
	private Map<Integer, String> getMonths(){
		Map<Integer, String> months = new HashMap<>();
		months.put(1,  "January");
		months.put(2,  "February");
		months.put(3,  "March");
		months.put(4,  "April");
		months.put(5,  "Map");
		months.put(6,  "June");
		months.put(7,  "July");
		months.put(8,  "August");
		months.put(9,  "September");
		months.put(10,  "October");
		months.put(11,  "November");
		months.put(12,  "December");
		return months;	
	}
	
    @GetMapping("/new")
    public String bookOfTheMonthForm(Model model) {
        model.addAttribute("months", getMonths());
        model.addAttribute("bookOfTheMonth", new BookOfTheMonth());
        return "monthly-books/new";
    }
    
    @PostMapping("/add")
    public String addBookOfTheMonth(
            @Valid @ModelAttribute("bookOfTheMonth") BookOfTheMonth bookOfTheMonth,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("months", getMonths());
            return "monthly-books/new";
        }

        bookOfTheMonthDao.add(bookOfTheMonth);
        return "redirect:/monthly-books/list";
    }

    @GetMapping("/remove/{id}")
    public String removeBookOfTheMonth(@PathVariable("id") String id) {
        bookOfTheMonthDao.remove(id);
        return "redirect:/monthly-books/list";
    }
}



