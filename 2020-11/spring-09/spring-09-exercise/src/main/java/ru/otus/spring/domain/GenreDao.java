package ru.otus.spring.domain;

import java.util.Optional;

public interface GenreDao {
    Optional<Genre> findById(long genred);
}
