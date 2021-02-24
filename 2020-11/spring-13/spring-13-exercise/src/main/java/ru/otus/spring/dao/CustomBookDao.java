package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.Optional;

public interface CustomBookDao {
    Optional<Book> customFindById(String bookId);
}
