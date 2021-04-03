package ru.otus.spring.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.domain.Genre;

public interface GenreDao extends ReactiveMongoRepository<Genre, Long> {
}
