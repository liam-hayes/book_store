package org.o7planning.sbbootstrap.bookstore.service;

import java.util.List;

import org.o7planning.sbbootstrap.bookstore.model.Book;
import org.o7planning.sbbootstrap.bookstore.repository.BookRepository;
import org.o7planning.sbbootstrap.bookstore.web.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	public Book findByTitle(String title) {
		return bookRepository.findByTitle(title);
	}
	public Book save(BookDto bookDto) {
		Book book = new Book();
		book.setAuthor(bookDto.getAuthor());
		book.setCategory(bookDto.getCategory());
		book.setPrice(bookDto.getPrice());
		book.setTitle(bookDto.getTitle());
		return bookRepository.save(book);
	}
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}
}
