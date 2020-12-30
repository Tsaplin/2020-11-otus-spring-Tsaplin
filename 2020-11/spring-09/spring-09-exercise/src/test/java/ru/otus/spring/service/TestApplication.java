package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.Main;
import ru.otus.spring.dao.*;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Запуск юнит-тестов")
@DataJpaTest
@Import({LibraryImpl.class, BookJpa.class, BookCommentJpa.class, AuthorJpa.class, GenreJpa.class})
/*
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})

*/
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
        long bookId = 1;

        res = library.bookInsert(1, 2, "Книга юнит-теста updateTest");
        if (1==1 /*bookDao.checkById(bookId)*/) {
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
        long bookId = 1;

        res = library.bookInsert(2, 3, "Книга юнит-теста deleteTest");
        if (1==1 /*bookDao.checkById(bookId)*/) {
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
        long bookId = 1;

       // Book book = new Book(bookId, 1, 1, "Книга юнит-теста getAllTest");
        library.bookInsert(1, 1, "Книга юнит-теста getAllTest");

        List<BookDto> lb = bookDao.getAll();
        for (int i = 0; i < lb.size(); i++) {
            BookDto b = lb.get(i);
            if (b.getBookId() == bookId) {
                actual = 1;
                break;
            }
        }
        assertEquals(1, actual);
    }

}
