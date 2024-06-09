package telran.java52.book.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java52.book.dao.AuthorRepository;
import telran.java52.book.dao.BookRepository;
import telran.java52.book.dao.PublisherRepository;
import telran.java52.book.dto.AuthorDto;
import telran.java52.book.dto.BookDto;
import telran.java52.book.dto.exception.EntityNotFoundException;
import telran.java52.book.model.Author;
import telran.java52.book.model.Book;
import telran.java52.book.model.Publisher;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	final BookRepository bookRepository;
	final AuthorRepository authorRepository;
	final PublisherRepository publisherRepository;
	final ModelMapper modelMapper;
	
	@Transactional
	@Override
	public Boolean addBook(BookDto bookDto) {
		if (bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		
		Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
				.orElse(publisherRepository.save(new Publisher(bookDto.getPublisher())));
		
		Set<Author> authors = bookDto.getAuthors().stream()
				.map(a -> authorRepository.findById(a.getName()).orElse(authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
				.collect(Collectors.toSet()); 
		
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepository.save(book);
		return true;
	}
	
	@Override
	public BookDto findBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional
	@Override
	public BookDto removeBook(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		bookRepository.delete(book);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional
	@Override
	public BookDto updateBook(String isbn, String title) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		book.setTitle(title);
		bookRepository.save(book);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public BookDto[] findByAuthor(String name) {
		Author author = authorRepository.findById(name).orElse(null);
		Set<Author> authors = new HashSet<Author>();
		authors.add(author);
							
		return bookRepository.findByAuthors(authors)
				.map(b -> modelMapper.map(b, BookDto.class))
				.toArray(BookDto[]::new);
	}

	@Transactional(readOnly = true)
	@Override
	public BookDto[] findByPublisher(String name) {
		Publisher publisher = publisherRepository.findById(name).orElse(null);
		
		return bookRepository.findByPublisher(publisher)
				.map(b -> modelMapper.map(b, BookDto.class))
				.toArray(BookDto[]::new);
	}

	@Override
	public AuthorDto[] findAuthorsbyBook(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		return book.getAuthors().stream()
				.map(a -> modelMapper.map(a, AuthorDto.class))
				.toArray(AuthorDto[]::new);
	}

	@Override
	public Publisher[] findPublishersByAuthor(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthorDto removeAuthor(String name) {
//		Author author = authorRepository.findById(name).orElseThrow(EntityNotFoundException::new);
//		authorRepository.delete(author);
//		return modelMapper.map(author, AuthorDto.class);
		return null;
	}
	
	

}
