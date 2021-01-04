package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class GenreJpa implements GenreDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public Optional<Genre> findById(long genreId) {
        return Optional.ofNullable(em.find(Genre.class, genreId));
    }

}
