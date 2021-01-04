package ru.otus.spring.dao;

import ru.otus.spring.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentDao {
    BookComment save(BookComment bc);
    void updateNameById(long bookCommentId, String comment);
    Optional<BookComment> findById(long bookCommentId);
    List<BookComment> getAll();
    void deleteById(long bookCommentId);
    void deleteByBook(long bookId);
}
