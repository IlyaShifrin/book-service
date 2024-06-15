package telran.java52.book.dao;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java52.book.model.Book;


public interface BookRepository extends JpaRepository<Book, String> {
	
	Stream<Book> findByAuthorsName(String name);
	
	Stream<Book> findByPublisherPublisherName(String name);
	
	void deleteByAuthorsName(String name);
}
