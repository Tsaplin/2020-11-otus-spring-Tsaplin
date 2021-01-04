package ru.otus.spring.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookDto  {
    private final long bookId;
    private final String bookName;
    private final long authorId;
    private final String authorFIO;
    private final long genreId;
    private final String genreName;
}
