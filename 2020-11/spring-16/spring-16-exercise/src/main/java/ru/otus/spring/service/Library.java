package ru.otus.spring.service;

public interface Library {
    boolean bookInsert(long authorId, long genreId, String bookName) throws Exception;
    boolean bookDelete(long bookId) throws Exception;
    boolean bookUpdate(long bookId, long authorId, long genreId, String bookName) throws Exception;
    void showCoomentsByBook(long bookId) throws Exception;
}
