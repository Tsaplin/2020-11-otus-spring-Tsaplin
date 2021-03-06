package ru.otus.spring.service;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.mongock.changelog.DatabaseChangelog;
import ru.otus.spring.mongock.changelog.DatabaseChangelogImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Запуск юнит-тестов")
@DataMongoTest
@Import({LibraryImpl.class, DatabaseChangelogImpl.class})
public class TestApplication {
    @Autowired
    AuthorDao authorDao;
    @Autowired
    GenreDao genreDao;

    @Autowired
    private Library library;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private DatabaseChangelog databaseChangelog;

    @Test
    @DisplayName("Запуск юнит-теста insertTest")
    public void insertTest()
    {
        boolean res = false;
        int actual = 0;

       // Предзаполнение БД
       databaseChangelog.dbPrepareData();

        try {
            res = library.bookInsert(1, 1, "Книга юнит-теста insertTest");
        } catch (Exception e) {
            e.printStackTrace();
        }

        actual = (res) ? 1 : 0;

        assertEquals(1, actual);
    }

    @Test
    @DisplayName("Запуск юнит-теста updateTest")
    public void updateTest()
    {
        boolean res = false;
        int actual = 0;
        String bookId = "0";
        String bookName = "Книга юнит-теста updateTest";

        // Предзаполнение БД
        databaseChangelog.dbPrepareData();

        try {
            res = library.bookInsert(1, 2, bookName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        val optionalBook = bookDao.findFirstByNameEquals(bookName);

        if (optionalBook.isPresent()) {
            bookId = optionalBook.get().getId();
            try {
                res = library.bookUpdate(bookId, 2, 3, "Книга юнит-теста updateTest модифицированная");
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        String bookId = "0";
        String bookName = "Книга юнит-теста deleteTest";

        // Предзаполнение БД
        databaseChangelog.dbPrepareData();

        try {
            res = library.bookInsert(2, 3, bookName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        val optionalBook = bookDao.findFirstByNameEquals(bookName);

        if (optionalBook.isPresent()) {
            bookId = optionalBook.get().getId();
            try {
                res = library.bookDelete(bookId);
            } catch (Exception e) {
                e.printStackTrace();
            }
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

        // Предзаполнение БД
        databaseChangelog.dbPrepareData();

        try {
            res = library.bookInsert(1, 1, bookName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Book> lb = bookDao.findBooksByAuthorNotNullAndGenreNotNull();
        for (int i = 0; i < lb.size(); i++) {
            Book b = lb.get(i);
            if (b.getName().equals(bookName)) {
                actual = 1;
                break;
            }
        }
        assertEquals(1, actual);
    }

}
