package telran.java52.book.dao;

import java.util.Set;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java52.book.model.Author;
import telran.java52.book.model.Book;
import telran.java52.book.model.Publisher;



public interface BookRepository extends JpaRepository<Book, String> {
	
	Stream<Book> findByAuthors(Set<Author> authors);
	
	Stream<Book> findByPublisher(Publisher publisher);
	
}
