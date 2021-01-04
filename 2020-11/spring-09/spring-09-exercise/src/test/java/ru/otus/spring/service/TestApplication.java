package ru.otus.spring.service;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.*;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookView;
import ru.otus.spring.dto.BookViewImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Запуск юнит-тестов")
@DataJpaTest
@Import({LibraryImpl.class, BookJpa.class, BookCommentJpa.class, AuthorJpa.class, GenreJpa.class, BookViewImpl.class})
public class TestApplication {
    @Autowired
    BookCommentDao bookCommentDao;
    @Autowired
    AuthorDao authorDao;
    @Autowired
    GenreDao genreDao;

    @Autowired
    private Library library;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookView bookView;

    @Test
    @DisplayName("Запуск юнит-теста insertTest")
    public void insertTest()
    {
        boolean res = false;
        int actual = 0;

        res = library.bookInsert(1, 1, "Книга юнит-теста insertTest");
        actual = (res) ? 1 : 0;

        assertEquals(1, actual);
    }

    @Test
    @DisplayName("Запуск юнит-теста updateTest")
    public void updateTest()
    {
        boolean res = false;
        int actual = 0;
        long bookId = 0;
        String bookName = "Книга юнит-теста updateTest";

        library.bookInsert(1, 2, bookName);
        val optionalBook = bookDao.findByName(bookName);

        if (optionalBook.isPresent()) {
            bookId = optionalBook.get().getBookId();
            res = library.bookUpdate(bookId, 2, 3, "Книга юнит-теста updateTest модифицированная");
        }

        actual = (res) ? 1 : 0;
        assertEquals(1, actual);
    }

    @Test
    @DisplayName("Запуск юнит-теста deleteTest")
    public void deleteTest()
    {
        boolean res = false;
        int actual = 0;
        long bookId = 0;
        String bookName = "Книга юнит-теста deleteTest";

        library.bookInsert(2, 3, bookName);
        val optionalBook = bookDao.findByName(bookName);

        if (optionalBook.isPresent()) {
            bookId = optionalBook.get().getBookId();
            res = library.bookDelete(bookId);
        }

        actual = (res) ? 1 : 0;
        assertEquals(1, actual);
    }

    @Test
    @DisplayName("Запуск юнит-теста getAllTest")
    public void getAllTest()
    {
        boolean res = false;
        int actual = 0;
        String bookName = "Книга юнит-теста getAllTest";

        library.bookInsert(1, 1, bookName);

        List<BookDto> lb = bookView.getAll();
        for (int i = 0; i < lb.size(); i++) {
            BookDto b = lb.get(i);
            if (b.getBookName().equals(bookName)) {
                actual = 1;
                break;
            }
        }
        assertEquals(1, actual);
    }

}
