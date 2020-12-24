package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.Main;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Запуск юнит-тестов")
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
public class TestApplication {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
    BookDao bookDao = context.getBean(BookDao.class);

    @Test
    @DisplayName("Запуск юнит-теста insertTest")
    public void insertTest()
    {
        boolean res = false;
        int actual = 0;
        Book book = new Book(1, 1, 2, "Книга юнит-теста insertTest");

        res = bookDao.insert(book);
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

        Book book = new Book(bookId, 1, 2, "Книга юнит-теста updateTest");
        bookDao.insert(book);
        Book book2 = new Book(bookId, 2, 3, "Книга юнит-теста updateTest модифицированная");

        res = bookDao.update(book2);
        actual = (res) ? 1 : 0;

        assertEquals(1, actual);
    }

    @Test
    @DisplayName("Запуск юнит-теста deleteTest")
    public void deleteTest()
    {
        boolean res = false;
        int actual = 0;
        long bookId = 2;

        Book book = new Book(bookId, 2, 3, "Книга юнит-теста deleteTest");
        bookDao.insert(book);

        res = bookDao.deleteById(bookId);
        actual = (res) ? 1 : 0;

        assertEquals(1, actual);
    }

    @Test
    @DisplayName("Запуск юнит-теста getAllTest")
    public void getAllTest()
    {
        boolean res = false;
        int actual = 0;
        long bookId = 3;

        Book book = new Book(bookId, 1, 1, "Книга юнит-теста getAllTest");
        bookDao.insert(book);

        List<Book> lb = bookDao.getAll();
        for (int i = 0; i < lb.size(); i++) {
            Book b = lb.get(i);
            if (b.getBookId() == bookId) {
                actual = 1;
                break;
            }
        }
        assertEquals(1, actual);
    }

}
