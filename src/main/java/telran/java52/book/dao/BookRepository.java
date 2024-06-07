package telran.java52.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java52.book.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}
