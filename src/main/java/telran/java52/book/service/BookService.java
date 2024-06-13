package telran.java52.book.service;

import java.util.List;

import telran.java52.book.dto.AuthorDto;
import telran.java52.book.dto.BookDto;

public interface BookService {
	
	Boolean addBook(BookDto bookDto);
	
	BookDto findBookByIsbn(String isbn);
	
	BookDto removeBook(String isbn);
	
	BookDto updateBook(String isbn, String title);
	
	Iterable<BookDto> findBooksByAuthor(String name);
	
	Iterable<BookDto> findBooksByPublisher(String name);
	
	Iterable<AuthorDto> findBookAuthors(String isbn);
	
	List<String> findPublishersByAuthor(String name);
	
	AuthorDto removeAuthor(String name);
}
