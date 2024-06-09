package telran.java52.book.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java52.book.dto.AuthorDto;
import telran.java52.book.dto.BookDto;
import telran.java52.book.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {

	final BookService bookService;

	@PostMapping("/book")
	public Boolean addBook(@RequestBody BookDto bookDto) {
		return bookService.addBook(bookDto);
	}

	@GetMapping("/book/{isbn}")
	public BookDto findBookByIsbn(@PathVariable String isbn) {
		return bookService.findBookByIsbn(isbn);
	}
	
	@DeleteMapping("/book/{isbn}")
	public BookDto removeBook(@PathVariable String isbn) {
		return bookService.removeBook(isbn);
	}
	
	@PutMapping("/book/{isbn}/title/{title}")
	public BookDto updateBook(@PathVariable String isbn, @PathVariable String title) {
		return bookService.updateBook(isbn, title);
	}
	
	@GetMapping("/books/author/{name}")
	public BookDto[] findByAuthor(@PathVariable String name) {
		return bookService.findByAuthor(name);
	}
	
	@GetMapping("/books/publisher/{name}")
	public BookDto[] findByPublisher(@PathVariable String name) {
		return bookService.findByPublisher(name);
	}
	
	@GetMapping("/authors/book/{isbn}")
	public AuthorDto[] findAuthorsByBook(@PathVariable String isbn) {
		return bookService.findAuthorsbyBook(isbn);
	}
	
	@GetMapping("/publishers/author/{name}")
	public String[] findPublishersByAuthor(@PathVariable String name) {
		return null;
	}
	
	@DeleteMapping("/author/{name}")
	public AuthorDto removeAuthor(@PathVariable String name) {
		return bookService.removeAuthor(name);
	}
}
