package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao extends JpaRepository<Book, Long> {
    @Transactional(readOnly = true)
    Optional<Book> findFirstByNameEquals(String bookName);

    @Transactional(readOnly = true)
    List<Book> findBooksByAuthorNotNullAndGenreNotNull();
}
