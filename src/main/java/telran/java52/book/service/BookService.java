package telran.java52.book.service;

import telran.java52.book.dto.AuthorDto;
import telran.java52.book.dto.BookDto;
import telran.java52.book.model.Publisher;

public interface BookService {
	
	Boolean addBook(BookDto bookDto);
	
	BookDto findBookByIsbn(String isbn);
	
	BookDto removeBook(String isbn);
	
	BookDto updateBook(String isbn, String title);
	
	BookDto[] findByAuthor(String name);
	
	BookDto[] findByPublisher(String name);
	
	AuthorDto[] findAuthorsbyBook(String isbn);
	
	String[] findPublishersByAuthor(String name);
	
	AuthorDto removeAuthor(String name);
}
