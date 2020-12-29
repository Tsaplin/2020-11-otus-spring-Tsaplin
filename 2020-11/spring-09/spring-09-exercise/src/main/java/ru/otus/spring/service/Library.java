package ru.otus.spring.service;

public interface Library {
    boolean bookInsert(long authorId, long genreId, String bookName);
}
