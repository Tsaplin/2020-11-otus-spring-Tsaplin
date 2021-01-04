package ru.otus.spring.dto;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;

import java.util.List;

public interface BookView {
    List<BookDto> getAll();
    List<BookComment> getCommentsByBook(Book book);
}
