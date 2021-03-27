package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;

import java.util.List;

public interface BookCommentDao extends JpaRepository<BookComment, Long> {
    @Transactional
    void deleteAllByBook(Book book);

    @Transactional(readOnly = true)
    List<BookComment> findAllByBook(Book book);
}
