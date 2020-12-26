package ru.otus.spring.dao;

import ru.otus.spring.domain.BookComment;

import java.util.List;

public interface BookCommentDao {
    boolean insert(BookComment bc);
    boolean update(BookComment bc);
    List<BookComment> getAll();
    boolean deleteById(long bookCommentId);
}
