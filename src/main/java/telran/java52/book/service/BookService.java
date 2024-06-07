package telran.java52.book.service;

import telran.java52.book.dto.BookDto;

public interface BookService {
	
	Boolean addBook(BookDto bookDto);
	
	BookDto findBookByIsbn(String isbn);
	
}
