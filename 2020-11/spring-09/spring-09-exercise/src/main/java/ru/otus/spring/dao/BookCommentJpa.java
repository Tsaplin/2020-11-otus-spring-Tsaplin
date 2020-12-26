package ru.otus.spring.dao;

import ru.otus.spring.domain.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class BookCommentJpa implements BookCommentDao {
    @PersistenceContext
    private EntityManager em;

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
    public boolean update(BookComment bc) {
        boolean result = false;
        try {

            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<BookComment> getAll() {
        ArrayList<BookComment> res = new ArrayList<BookComment>();
        return res;
    }

    @Override
    public boolean deleteById(long bookCommentId) {
        boolean result = false;
        try {

            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
