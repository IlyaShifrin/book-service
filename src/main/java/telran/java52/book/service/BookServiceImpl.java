package telran.java52.book.service;

import java.util.List;
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
		bookRepository.deleteById(isbn);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional
	@Override
	public BookDto updateBook(String isbn, String title) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		book.setTitle(title);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<BookDto> findBooksByAuthor(String name) {
		return bookRepository.findByAuthorsName(name)
				.map(b -> modelMapper.map(b, BookDto.class))
				.toList();
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<BookDto> findBooksByPublisher(String name) {
		return bookRepository.findByPublisherPublisherName(name)
				.map(b -> modelMapper.map(b, BookDto.class))
				.toList();
	}

	@Override
	public Iterable<AuthorDto> findBookAuthors(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		return book.getAuthors().stream()
				.map(a -> modelMapper.map(a, AuthorDto.class))
				.toList();
	}

	@Override
	public List<String> findPublishersByAuthor(String name) {
		return publisherRepository.findPublishersByAuthor(name);
	}

	@Transactional
	@Override
	public AuthorDto removeAuthor(String name) {
		Author author = authorRepository.findById(name).orElseThrow(EntityNotFoundException::new);
//		bookRepository.findByAuthorsName(name)
//						.forEach(b -> bookRepository.delete(b));
		bookRepository.deleteByAuthorsName(name);
		authorRepository.deleteById(name);
		return modelMapper.map(author, AuthorDto.class);
	}

}
