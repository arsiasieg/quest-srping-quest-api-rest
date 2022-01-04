package com.wcs.libraryapirest.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wcs.libraryapirest.entity.Book;
import com.wcs.libraryapirest.repository.BookRepository;

@RestController
public class BookController {

	@Autowired
	BookRepository bookRepository;
	
	@GetMapping("/booksall")
	public List<Book> getAllBook(){
		return bookRepository.findAll();
	}
	
	@GetMapping("/book/{id}")
	public Book getById(@PathVariable Long id){
		Book book = new Book();
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			book =  optionalBook.get();
		}
		
		return book;
		
	}
	
	@PostMapping("/books/search")
	public Book searchByTitleOrDescription(@RequestBody Map<String, String> body){
		String searchTerm = body.get("text");
		return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
	}
	
	@PostMapping("/bookcreate")
	public Book createBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	@PutMapping("/bookupdate/{idBook}")
	public Book updateBook(@PathVariable Long idBook, @RequestBody Book book) {
		Book bookToUpdate = new Book();
		
		Optional<Book> optionalBook = bookRepository.findById(idBook);
		if(optionalBook.isPresent()) {
			bookToUpdate = optionalBook.get();
		}
		
		bookToUpdate.setTitle(book.getTitle());
		bookToUpdate.setAuthor(book.getAuthor());
		bookToUpdate.setDescription(book.getDescription());
		
		return bookRepository.save(bookToUpdate);
	}
	
	@DeleteMapping("/bookdelete/{idBook}")
	public void deleteBook(@PathVariable Long idBook) {
		bookRepository.deleteById(idBook);
	}
}
