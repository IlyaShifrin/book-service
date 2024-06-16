package telran.java52.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import telran.java52.book.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Stream<Book> findByAuthorsName(String name) {
		@SuppressWarnings("unchecked")
		Stream<Book> resList = em.createQuery("select b from Author a join a.books b where a.name=?1")
		.setParameter(1, name)
		.getResultStream();
		
		return resList;
	}

	@Override
	public Stream<Book> findByPublisherPublisherName(String name) {
		@SuppressWarnings("unchecked")
		Stream<Book> resList = em.createQuery("select b from Publisher p join p.books b where p.publisherName=?1")
		.setParameter(1, name)
		.getResultStream();
		
		return resList;
	}

	// Не нашел где используется этот метод
	@Transactional
	@Override
	public void deleteByAuthorsName(String name) {
		findByAuthorsName(name)
			.forEach(b -> em.remove(b));
	}

	@Override
	public boolean existsById(String isbn) {
		return em.find(Book.class, isbn) != null;
	}

	@Override
	public Book save(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public Optional<Book> findById(String isbn) {
		return Optional.ofNullable(em.find(Book.class, isbn));
	}

	@Override
	public void deleteById(String isbn) {
		Book book = em.find(Book.class, isbn);
		em.remove(book);
	}

}
