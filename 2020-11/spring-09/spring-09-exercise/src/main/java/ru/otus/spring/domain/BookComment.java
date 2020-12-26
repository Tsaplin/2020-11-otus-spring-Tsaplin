package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookComment {
    private final long bookCommentId;
    private final long bookId;
    private final String comment;
}

