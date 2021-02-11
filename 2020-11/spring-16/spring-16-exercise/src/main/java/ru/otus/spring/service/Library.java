package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface Library {
    List<Genre> getAllGenres();
    List<Author> getAllAuthors();
    Optional<Book> bookShow(long bookId);
    boolean bookInsert(long authorId, long genreId, String bookName) throws Exception;
    boolean bookDelete(long bookId) throws Exception;
    boolean bookUpdate(long bookId, long authorId, long genreId, String bookName) throws Exception;
    void showCoomentsByBook(long bookId) throws Exception;
    List<Book> showAllBooks() throws Exception;
}
