package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookCommentJpa implements BookCommentDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = false)
    @Override
    public BookComment save(BookComment bc) {
        if (bc.getBookCommentId() <= 0) {
            em.persist(bc);
            return bc;
        }
        else {
            return em.merge(bc);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookComment> getAll() {
        TypedQuery<BookComment> query = em.createQuery("select bc from BookComment bc", BookComment.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BookComment> findById(long bookCommentId) {
        return Optional.ofNullable(em.find(BookComment.class, bookCommentId));
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(long bookCommentId) {
        BookComment bc = em.find(BookComment.class, bookCommentId);
        em.remove(bc);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteByBook(Book book) {
        Query query = em.createQuery("delete " +
                                        "from BookComment bc " +
                                        "where bc.book = :book");
        query.setParameter("book", book);
        query.executeUpdate();
    }

}
