package ru.otus.spring.dao;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class BookCommentJpa implements BookCommentDao {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private BookDao bd;

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

    @Override
    public List<BookComment> getAll() {
        TypedQuery<BookComment> query = em.createQuery("select bc from BookComment bc", BookComment.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long bookCommentId) {
        Query query = em.createQuery("delete " +
                                        "from BookComment bc " +
                                        "where bc.bookCommentId = :bookCommentId");
        query.setParameter("bookCommentId", bookCommentId);
        query.executeUpdate();
    }

    @Override
    public void deleteByBook(long bookId) {
        val optionalBook = bd.findById(bookId);
        Query query = em.createQuery("delete " +
                                        "from BookComment bc " +
                                        "where bc.book = :book");
        query.setParameter("book", optionalBook.get());
        query.executeUpdate();
    }


    @Override
    public void updateNameById(long bookCommentId, String comment) {
        Query query = em.createQuery("update BookComment bc " +
                                        "set bc.comment = :comment " +
                                        "where bc.bookCommentId = :bookCommentId");
        query.setParameter("comment", comment);
        query.setParameter("bookCommentId", bookCommentId);
        query.executeUpdate();
    }
}
