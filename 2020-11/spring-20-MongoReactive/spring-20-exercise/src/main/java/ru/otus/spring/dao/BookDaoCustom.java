package ru.otus.spring.dao;

import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Book;

public interface BookDaoCustom {
    Mono<Book> customFindById(String bookId);
}
