package com.bookclub.bookclub.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookclub.bookclub.model.WishlistItem;
import com.bookclub.bookclub.service.impl.MemWishlistDao;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String showWishlist(Model model){
		
        MemWishlistDao dao = new MemWishlistDao();
        List<WishlistItem> wishlist = dao.list();
        model.addAttribute("wishlist", wishlist);
        return "wishlist/list";
		
	}
	
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String wishlistForm(Model model){
		 model.addAttribute("wishlistItem", new WishlistItem());
		 return "wishlist/new";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult){
		System.out.println(wishlistItem.toString());
		System.out.println(bindingResult.getAllErrors());
		
		if(bindingResult.hasErrors()) {
			return "wishlist/new";
		}
		
		return "redirect:/wishlist";
}

}
