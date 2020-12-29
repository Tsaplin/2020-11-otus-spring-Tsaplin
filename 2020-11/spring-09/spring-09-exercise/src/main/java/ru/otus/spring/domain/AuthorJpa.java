package ru.otus.spring.domain;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;


@Transactional
@Repository
public class AuthorJpa implements AuthorDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Author> findById(long authorId) {
        return Optional.ofNullable(em.find(Author.class, authorId));
    }

}
