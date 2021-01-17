package ru.otus.spring.service;

import org.springframework.shell.standard.ShellOption;

public interface Library {
    boolean bookInsert(long authorId, long genreId, String bookName) throws Exception;
    boolean bookDelete(long bookId) throws Exception;
    boolean bookUpdate(long bookId, @ShellOption(defaultValue = "-1") long authorId, @ShellOption(defaultValue = "-1") long genreId, @ShellOption(defaultValue = ShellOption.NULL) String bookName) throws Exception;
    void showCoomentsByBook(long bookId) throws Exception;
}
