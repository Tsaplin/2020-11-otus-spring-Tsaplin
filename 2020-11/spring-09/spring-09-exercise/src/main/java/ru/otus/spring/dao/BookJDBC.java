package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookJDBC implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookJDBC(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from tBook", Integer.class);
    }
/*
    @Override
    public boolean checkById(long bookId) {
        int exist = 0;

        exist = namedParameterJdbcOperations.getJdbcOperations().queryForObject(
                "select count(1) from tBook where BookID = ?", new Object[] {bookId}, Integer.class);

        return (exist == 1);
    }

    @Override
    public boolean insert(Book book) {
        boolean result = false;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("bookId", book.getBookId());
            params.put("authorId", book.getAuthorId());
            params.put("genreId", book.getGenreId());
            params.put("bookName", book.getName());
            namedParameterJdbcOperations.update(
                    "insert into tBook (BookID, AuthorID, GenreID, `Name`) values (:bookId, :authorId, :genreId, :bookName)",
                    params);

            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean update(Book book) {
        boolean result = false;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("bookId", book.getBookId());
            params.put("authorId", book.getAuthorId());
            params.put("genreId", book.getGenreId());
            params.put("bookName", book.getName());
            namedParameterJdbcOperations.update(
                    "update tBook set AuthorID = isnull(nullif(:authorId, -1), AuthorID), GenreID = isnull(nullif(:genreId, -1), GenreID), `Name` = isnull(:bookName, `Name`) where BookID = :bookId",
                    params);

            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<BookDto> getAll() {
        return namedParameterJdbcOperations.query(
                "select b.BookID, b.Name as bookName, b.AuthorID, a.FIO, b.GenreID, g.Name as genreName from tBook b, tAuthors a, tGenre g where b.AuthorID = a.AuthorID and b.GenreID = g.GenreID",
                new BookDtoMapper());
    }

    @Override
    public boolean deleteById(long bookId) {
        boolean result = false;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("bookId", bookId);
            namedParameterJdbcOperations.update(
                    "delete from tBook where BookID = :bookId", params
            );

            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

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
