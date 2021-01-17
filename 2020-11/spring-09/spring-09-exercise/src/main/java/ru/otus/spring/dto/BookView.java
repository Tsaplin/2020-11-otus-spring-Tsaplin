package ru.otus.spring.dto;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookView {
    List<BookDto> getAll();
    List<BookCommentView> getCommentsByBook(Book book);
}
