package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookCommentDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookView;

import java.util.List;

// Класс работы с приложением "Библиотека книг"
@ShellComponent
@RequiredArgsConstructor
public class LibraryImpl implements Library {

    private final BookDao bookDao;
    private final BookView bookView;
    private final BookCommentDao bookCommentDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final String BOOK_NOT_EXIST_MESSAGE = "Книга не существует в базе данных.";

    private static Logger logger = LogManager.getLogger();

    @ShellMethod(value = "Add a new book in format:ins authorId genreId bookName", key = {"ins", "insert"})
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

    @ShellMethod(value = "Modify the selected book in format:upd bookId authorId genreId bookName", key = {"upd", "update"})
    public boolean bookUpdate(long bookId, @ShellOption(defaultValue = "-1") long authorId, @ShellOption(defaultValue = "-1") long genreId, @ShellOption(defaultValue = ShellOption.NULL) String bookName) throws Exception {
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

    @ShellMethod(value = "Delete the selected book in format:del bookId", key = {"del", "delete"})
    public boolean bookDelete(long bookId) throws Exception {
        boolean result = false;
        try {
            val optionalBook = bookDao.findById(bookId);
            if (optionalBook.isPresent()) {
                bookCommentDao.deleteByBook(optionalBook.get());
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

    @ShellMethod(value = "Show all the books in the library", key = {"show"})
    public void showAllBooks() throws Exception {
        try {
            List<BookDto> lb = bookView.getAll();
            lb.forEach(logger::info);
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e);
        }
    }

    @ShellMethod(value = "Show comments of selected book in format:comments bookId", key = {"comments"})
    public void showCoomentsByBook(long bookId) throws Exception {
        try {
            val optionalBook = bookDao.findById(bookId);
            if (optionalBook.isPresent()) {
                List<BookComment> lbc = bookView.getCommentsByBook(optionalBook.get());
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
