package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookJpa implements BookDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public int count() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList().size();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(long bookId) {
        return Optional.ofNullable(em.find(Book.class, bookId));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findByName(String bookName) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.name = :bookName", Book.class);
        query.setParameter("bookName", bookName);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            return Optional.ofNullable(null);
        }
    }

    @Transactional(readOnly = false)
    @Override
    public Book save(Book book) {
        if (book.getBookId() <= 0) {
            em.persist(book);
            return book;
        }
        else {
            return em.merge(book);
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(long bookId) {
        Query query = em.createQuery("delete " +
                                        "from Book b " +
                                        "where b.bookId = :bookId");
        query.setParameter("bookId", bookId);
        query.executeUpdate();
    }

}