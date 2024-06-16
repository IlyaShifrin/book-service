package telran.java52.book.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import telran.java52.book.model.Publisher;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

	@PersistenceContext
	EntityManager em;
	
	// Это такой же метод по сути, как метод ниже - findDistinctByBooksAuthorsName?  
	@Override
	public List<String> findPublishersByAuthor(String authorName) {
		
		return null;
	}

	@Override
	public Stream<Publisher> findDistinctByBooksAuthorsName(String authorName) {
		@SuppressWarnings("unchecked")
		Stream<Publisher> resList = em.createQuery("select distinct p from Publisher p join p.books b join b.authors a where a.name=?1")
		.setParameter(1, authorName)
		.getResultStream();
		
		return resList;
	}

	@Override
	public Optional<Publisher> findById(String publisher) {
		return Optional.ofNullable(em.find(Publisher.class, publisher));
	}

//	@Transactional
	@Override
	public Publisher save(Publisher publisher) {
		em.persist(publisher);
//		em.merge(publisher);
		return publisher;
	}

}
