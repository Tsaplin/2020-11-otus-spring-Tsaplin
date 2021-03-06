package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface Library {
    List<Genre> getAllGenres();
    List<Author> getAllAuthors();
    Optional<Book> bookShow(String bookId);
    boolean bookInsert(long authorId, long genreId, String bookName) throws Exception;
    boolean bookDelete(String bookId) throws Exception;
    boolean bookUpdate(String bookId, long authorId, long genreId, String bookName) throws Exception;
    List<Book> showAllBooks();
}
