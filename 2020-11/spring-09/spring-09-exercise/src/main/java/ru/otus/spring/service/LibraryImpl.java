package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.*;
import ru.otus.spring.dto.BookDto;

import java.util.List;

// Класс работы с приложением "Библиотека книг"
@ShellComponent
@RequiredArgsConstructor
public class LibraryImpl implements Library {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final String BOOK_NOT_EXIST_MESSAGE = "Книга не существует в базе данных.";

    @ShellMethod(value = "Add a new book in format:ins authorId genreId bookName", key = {"ins", "insert"})
    public boolean bookInsert(long authorId, long genreId, String bookName) {
        boolean result = false;

        Author author = authorDao.findById(authorId).get();
        Genre genre = genreDao.findById(genreId).get();
        Book b = new Book(0, author, genre, bookName);
        try {
            bookDao.save(b);
            result = true;
            System.out.println("Книга успешно добавлена.");
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @ShellMethod(value = "Modify the selected book in format:upd bookId authorId genreId bookName", key = {"upd", "update"})
    public boolean bookUpdate(long bookId, @ShellOption(defaultValue = "-1") long authorId, @ShellOption(defaultValue = "-1") long genreId, @ShellOption(defaultValue = ShellOption.NULL) String bookName) {
        boolean result = false;

        Author author = authorDao.findById(authorId).get();
        Genre genre = genreDao.findById(genreId).get();
        Book b = new Book(bookId, author, genre, bookName);
        try {
           // result = bookDao.checkById(bookId);
            if (/*result*/ 1 == 1) {
               bookDao.save(b);
               result = true;
               System.out.println("Книга успешно изменена.");
            }
            else {
                System.out.println(BOOK_NOT_EXIST_MESSAGE);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @ShellMethod(value = "Delete the selected book in format:del bookId", key = {"del", "delete"})
    public boolean bookDelete(long bookId) {
        boolean result = false;
        try {
           // result = bookDao.checkById(bookId);
            if (/*result*/1==1) {
                bookDao.deleteById(bookId);
                result = true;
                System.out.println("Книга успешно удалена.");
            }
            else {
                System.out.println(BOOK_NOT_EXIST_MESSAGE);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @ShellMethod(value = "Show all the books in the library", key = {"show"})
    public void showAllBooks() {
        try {
            List<BookDto> lb = bookDao.getAll();
            lb.forEach(System.out::println);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
