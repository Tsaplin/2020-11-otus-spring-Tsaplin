package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentDao extends JpaRepository<BookComment, Long> {
    BookComment save(BookComment bc);
    Optional<BookComment> findById(long bookCommentId);
    List<BookComment> findAll();
    void deleteById(long bookCommentId);

    @Transactional
    void deleteAllByBook(Book book);
}
