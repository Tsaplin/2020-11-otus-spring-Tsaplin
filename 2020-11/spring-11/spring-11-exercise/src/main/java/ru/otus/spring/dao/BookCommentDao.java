package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentDao extends JpaRepository<BookComment, Long> {
    BookComment save(BookComment bc);
    Optional<BookComment> findById(long bookCommentId);
    List<BookComment> findAll();
    void deleteById(long bookCommentId);
    void deleteAllByBook(Book book); // !!! No EntityManager with actual transaction available for current thread
}
