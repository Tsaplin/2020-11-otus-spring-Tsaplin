package ru.otus.spring.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Book;

import java.util.Optional;

public interface BookDao extends ReactiveMongoRepository<Book, String>, BookDaoCustom {
    @Transactional(readOnly = true)
    Optional<Book> findFirstByNameEquals(String bookName);

    @Transactional(readOnly = true)
    Flux<Book> findBooksByAuthorNotNullAndGenreNotNull();
}
