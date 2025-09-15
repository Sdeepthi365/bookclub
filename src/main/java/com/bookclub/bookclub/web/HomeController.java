package com.bookclub.bookclub.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;                 // <-- Spring's Model
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome(Model model) {
        return "index";      // templates/index.html
    }

    @GetMapping("/about")
    public String showAboutUs(Model model) {
        return "about";      // templates/about.html
    }

    @GetMapping("/contact")
    public String showContactUs(Model model) {
        return "contact";    // templates/contact.html
    }
}
