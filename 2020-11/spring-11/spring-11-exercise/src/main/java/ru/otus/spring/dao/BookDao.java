package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Book;

import java.util.Optional;

public interface BookDao extends JpaRepository<Book, Long> {
    Book save(Book book);
    void deleteById(long bookId);
    long count();
    Optional<Book> findById(long bookId);
    Optional<Book> findFirstByNameEquals(String bookName);
}
