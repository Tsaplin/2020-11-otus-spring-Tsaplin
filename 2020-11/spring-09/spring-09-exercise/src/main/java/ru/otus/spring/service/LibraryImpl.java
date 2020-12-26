package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookDto;

import java.util.List;

// Класс работы с приложением "Библиотека книг"
@ShellComponent
@RequiredArgsConstructor
public class LibraryImpl {

    private final BookDao bookDao;
    private final String BOOK_NOT_EXIST_MESSAGE = "Книга не существует в базе данных.";

    @ShellMethod(value = "Add a new book in format:ins bookId authorId genreId bookName", key = {"ins", "insert"})
    public boolean bookInsert(long bookId, long authorId, long genreId, String bookName) {
        boolean result = false;
        //Book b = new Book(bookId, authorId, genreId, bookName);
        try {
           // result = bookDao.insert(b);
            System.out.println("Книга успешно добавлена.");
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @ShellMethod(value = "Modify the selected book in format:upd bookId authorId genreId bookName", key = {"upd", "update"})
    public boolean bookUpdate(long bookId, @ShellOption(defaultValue = "-1") long authorId, @ShellOption(defaultValue = "-1") long genreId, @ShellOption(defaultValue = ShellOption.NULL) String bookName) {
        boolean result = false;
        //Book b = new Book(bookId, authorId, genreId, bookName);
        try {
           // result = bookDao.checkById(bookId);
            if (result) {
              // result = bookDao.update(b);
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
            if (result) {
               // result = bookDao.deleteById(bookId);
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
           // List<BookDto> lb = bookDao.getAll();
           // lb.forEach(System.out::println);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
