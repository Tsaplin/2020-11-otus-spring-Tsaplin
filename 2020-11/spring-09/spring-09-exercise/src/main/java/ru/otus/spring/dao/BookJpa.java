package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.dto.BookDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
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
/*
    @Override
    public boolean checkById(long bookId) {
        int exist = 0;

        exist = namedParameterJdbcOperations.getJdbcOperations().queryForObject(
                "select count(1) from tBook where BookID = ?", new Object[] {bookId}, Integer.class);

        return (exist == 1);
    }
*/
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

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAll() {
        Query query = em.createQuery("select new ru.otus.spring.dto.BookDto ( b.bookId, b.name, a.authorId, a.FIO, g.genreId, g.Name) from Book b join b.author a join b.genre g", BookDto.class);
        return query.getResultList();
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
/*
    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long bookId = resultSet.getLong("BookID");
            long authorId = resultSet.getLong("AuthorID");
            long genreId = resultSet.getLong("GenreID");
            String bookName = resultSet.getString("Name");
            return new Book(bookId, authorId, genreId, bookName);
        }
    }

    private static class BookDtoMapper implements RowMapper<BookDto> {
        @Override
        public BookDto mapRow(ResultSet resultSet, int i) throws SQLException {
            long bookId = resultSet.getLong("BookID");
            String bookName = resultSet.getString("bookName");
            long authorId = resultSet.getLong("AuthorID");
            String authorFIO = resultSet.getString("FIO");
            long genreId = resultSet.getLong("GenreID");
            String genreName = resultSet.getString("genreName");

            return new BookDto(bookId, bookName, authorId, authorFIO, genreId, genreName);
        }
    }
*/
}
