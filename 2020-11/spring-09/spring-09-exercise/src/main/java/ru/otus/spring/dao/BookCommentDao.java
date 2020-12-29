package ru.otus.spring.dao;

import ru.otus.spring.domain.BookComment;

import java.util.List;

public interface BookCommentDao {
    BookComment save(BookComment bc);
    void updateNameById(long bookCommentId, String comment);
    List<BookComment> getAll();
    void deleteById(long bookCommentId);
}
