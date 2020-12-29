package ru.otus.spring.domain;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Transactional
@Repository
public class GenreJpa implements GenreDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Genre> findById(long genreId) {
        return Optional.ofNullable(em.find(Genre.class, genreId));
    }

}
