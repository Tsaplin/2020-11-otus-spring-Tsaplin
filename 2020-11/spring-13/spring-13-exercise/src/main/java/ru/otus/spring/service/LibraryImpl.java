package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

// Класс работы с приложением "Библиотека книг"
@RequiredArgsConstructor
public class LibraryImpl implements Library {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final String BOOK_NOT_EXIST_MESSAGE = "Книга не существует в базе данных.";

    private static Logger logger = LogManager.getLogger();

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.findAll();
    }

    @Override
    public Optional<Book> bookShow(String bookId) {
        return bookDao.customFindById(bookId);
    }

    @Override
    public boolean bookInsert(long authorId, long genreId, String bookName) throws Exception {
        boolean result = false;

        Author author = authorDao.findById(authorId).get();
        Genre genre = genreDao.findById(genreId).get();
        Book b = new Book(null, author, genre, bookName);
        try {
            bookDao.save(b);
            result = true;
            logger.info("Книга успешно добавлена.");
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e);
        }

        return result;
    }

    @Override
    public boolean bookUpdate(String bookId, long authorId, long genreId, String bookName) throws Exception {
        boolean result = false;

        Author author = authorDao.findById(authorId).get();
        Genre genre = genreDao.findById(genreId).get();
        Book b = new Book(bookId, author, genre, bookName);
        try {
            val optionalBook = bookDao.customFindById(bookId);
            if (optionalBook.isPresent()) {
                bookDao.save(b);
                result = true;
                logger.info("Книга успешно изменена.");
            }
            else {
               logger.error(BOOK_NOT_EXIST_MESSAGE);
            }
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e);
        }

        return result;
    }

    @Override
    public boolean bookDelete(String bookId) throws Exception {
        boolean result = false;
        try {
            val optionalBook = bookDao.customFindById(bookId);
            if (optionalBook.isPresent()) {
                bookDao.deleteById(bookId);
                result = true;
                logger.info("Книга успешно удалена.");
            }
            else {
                logger.info(BOOK_NOT_EXIST_MESSAGE);
            }
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e);
        }

        return result;
    }

    @Override
    public List<Book> showAllBooks() {
        return bookDao.findBooksByAuthorNotNullAndGenreNotNull();
    }

}
