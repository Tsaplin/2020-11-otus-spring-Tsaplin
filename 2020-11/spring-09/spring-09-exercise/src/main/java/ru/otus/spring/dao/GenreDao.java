package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.Optional;

public interface GenreDao {
    Optional<Genre> findById(long genred);
}
