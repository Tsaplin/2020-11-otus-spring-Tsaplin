package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {
    boolean insert(Book book);
    boolean update(Book book);
    List<Book> getAll();
    boolean deleteById(long bookId);
    int count();
}
