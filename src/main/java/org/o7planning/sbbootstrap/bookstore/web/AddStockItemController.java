package org.o7planning.sbbootstrap.bookstore.web;

import javax.validation.Valid;

import org.o7planning.sbbootstrap.bookstore.model.Book;
import org.o7planning.sbbootstrap.bookstore.service.BookService;
import org.o7planning.sbbootstrap.bookstore.web.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add-stock-item")
public class AddStockItemController {
	@Autowired
	private BookService bookService;

    @ModelAttribute("book")
    public BookDto bookDto() {
        return new BookDto();
    }

    @GetMapping
    public String showStockItemForm(Model model) {
        return "add-stock-item";
    }

    @PostMapping
    public String addBook(@ModelAttribute("book") @Valid BookDto bookDto,
                                      BindingResult result){
    	if (result.hasErrors()){
            return "add-stock-item";
        }
        Book existing = bookService.findByTitle(bookDto.getTitle());
        if (existing != null){
        	existing.incrementStockLevel();
        	bookService.save(existing);
        }
        else {
            bookService.save(bookDto);
        }

        return "redirect:/add-stock-item?success";
    }
}
