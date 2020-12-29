package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.Optional;

public interface AuthorDao {
    Optional<Author> findById(long authorId);
}
