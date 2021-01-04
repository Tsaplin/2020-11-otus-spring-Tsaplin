package ru.otus.spring.dto;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookViewImpl implements BookView {
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAll() {
        Query query = em.createQuery("select new ru.otus.spring.dto.BookDto ( b.bookId, b.name, a.authorId, a.FIO, g.genreId, g.Name) from Book b join b.author a join b.genre g", BookDto.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookComment> getByBook(Book book) {
        Query query = em.createQuery("select bc from BookComment bc where bc.book = :book");
        query.setParameter("book", book);
        return query.getResultList();
    }

}
