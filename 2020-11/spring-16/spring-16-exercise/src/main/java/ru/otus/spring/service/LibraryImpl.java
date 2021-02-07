package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookCommentDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

// Класс работы с приложением "Библиотека книг"
@RequiredArgsConstructor
public class LibraryImpl implements Library {

    private final BookDao bookDao;
    private final BookCommentDao bookCommentDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final String BOOK_NOT_EXIST_MESSAGE = "Книга не существует в базе данных.";

    private static Logger logger = LogManager.getLogger();

    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }

    public List<Author> getAllAuthors() {
        return authorDao.findAll();
    }

    public Optional<Book> bookShow(long bookId) {
        return bookDao.findById(bookId);
    }

    public boolean bookInsert(long authorId, long genreId, String bookName) throws Exception {
        boolean result = false;

        Author author = authorDao.findById(authorId).get();
        Genre genre = genreDao.findById(genreId).get();
        Book b = new Book(0, author, genre, bookName);
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

    public boolean bookUpdate(long bookId, long authorId, long genreId, String bookName) throws Exception {
        boolean result = false;

        Author author = authorDao.findById(authorId).get();
        Genre genre = genreDao.findById(genreId).get();
        Book b = new Book(bookId, author, genre, bookName);
        try {
            val optionalBook = bookDao.findById(bookId);
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

    public boolean bookDelete(long bookId) throws Exception {
        boolean result = false;
        try {
            val optionalBook = bookDao.findById(bookId);
            if (optionalBook.isPresent()) {
                bookCommentDao.deleteAllByBook(optionalBook.get());
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

    public void showAllBooks() throws Exception {
        try {
            List<Book> lb = bookDao.findBooksByAuthorNotNullAndGenreNotNull();
            lb.forEach(logger::info);
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e);
        }
    }

    @Transactional
    public void showCoomentsByBook(long bookId) throws Exception {
        try {
            val optionalBook = bookDao.findById(bookId);
            if (optionalBook.isPresent()) {
                List<BookComment> lbc = bookCommentDao.findAllByBook(optionalBook.get());
                System.out.println("Комментарии книги bookId=" + String.valueOf(bookId));
                lbc.forEach(System.out::println);
            } else {
                logger.info(BOOK_NOT_EXIST_MESSAGE);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e);
        }
    }

}
