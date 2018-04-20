package org.o7planning.sbbootstrap.bookstore.web;

import java.util.Collection;
import java.util.List;

import org.o7planning.sbbootstrap.bookstore.model.Book;
import org.o7planning.sbbootstrap.bookstore.service.BookService;
import org.o7planning.sbbootstrap.security.model.User;
import org.o7planning.sbbootstrap.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	public boolean manager;
	public boolean player;
	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@ModelAttribute("role")
	public String getRole() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		@SuppressWarnings("unchecked")
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) authentication
				.getAuthorities();
		for (SimpleGrantedAuthority i : authorities) {
			if (i.getAuthority().equals("customer")) {
				return "customer";
			}
		}
		return "administrator";
	}

	@ModelAttribute("user")
	public User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalEmail = authentication.getName();
		User u = userService.findByEmail(currentPrincipalEmail);
		return u;
	}

	@ModelAttribute("books")
	public List<Book> getBooks() {
		return bookService.getBooks();
	}

	@GetMapping
	public String showIndex(Model model) {
		return "index";
	}
}