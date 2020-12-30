package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book save(Book book);
    List<BookDto> getAll();
    void deleteById(long bookId);
    int count();
    Optional<Book> findById(long bookId);
    Optional<Book> findByName(String bookName);
}
