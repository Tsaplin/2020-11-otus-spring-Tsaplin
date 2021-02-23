package ru.otus.spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao extends MongoRepository<Book, String> {
    @Transactional(readOnly = true)
    Optional<Book> findFirstByNameEquals(String bookName);

    @Transactional(readOnly = true)
    List<Book> findBooksByAuthorNotNullAndGenreNotNull();
}
