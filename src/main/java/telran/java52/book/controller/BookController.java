package telran.java52.book.controller;

import java.util.List;

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
	public Iterable<BookDto> findBooksByAuthor(@PathVariable String name) {
		return bookService.findBooksByAuthor(name);
	}
	
	@GetMapping("/books/publisher/{name}")
	public Iterable<BookDto> findBooksByPublisher(@PathVariable String name) {
		return bookService.findBooksByPublisher(name);
	}
	
	@GetMapping("/authors/book/{isbn}")
	public Iterable<AuthorDto> findBookAuthors(@PathVariable String isbn) {
		return bookService.findBookAuthors(isbn);
	}
	
	@GetMapping("/publishers/author/{name}")
	public Iterable<String> findPublishersByAuthor(@PathVariable String name) {
		return bookService.findPublishersByAuthor(name);
	}
	
	@DeleteMapping("/author/{name}")
	public AuthorDto removeAuthor(@PathVariable String name) {
		return bookService.removeAuthor(name);
	}
}
