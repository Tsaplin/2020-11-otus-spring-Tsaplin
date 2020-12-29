package ru.otus.spring.domain;

import java.util.Optional;

public interface AuthorDao {
    Optional<Author> findById(long authorId);
}
