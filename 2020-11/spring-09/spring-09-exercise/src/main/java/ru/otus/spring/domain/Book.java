package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Book {
   private final long bookId;
   private final long authorId;
   private final long genreId;
   private final String name;
}
