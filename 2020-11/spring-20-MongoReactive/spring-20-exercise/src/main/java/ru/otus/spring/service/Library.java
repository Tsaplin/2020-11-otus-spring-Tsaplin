package ru.otus.spring.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

public interface Library {
    Flux<Genre> getAllGenres();
    Flux<Author> getAllAuthors();
    Mono<Book> bookShow(String bookId);
    boolean bookInsert(long authorId, long genreId, String bookName) throws Exception;
    boolean bookDelete(String bookId) throws Exception;
    boolean bookUpdate(String bookId, long authorId, long genreId, String bookName) throws Exception;
    Flux<Book> showAllBooks();
}
