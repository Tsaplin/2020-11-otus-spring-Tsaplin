package ru.otus.spring.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.domain.Author;

public interface AuthorDao extends ReactiveMongoRepository<Author, Long> {
}
