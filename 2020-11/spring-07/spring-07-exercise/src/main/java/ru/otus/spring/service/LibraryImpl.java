package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;
import java.util.List;

// Класс работы с приложением "Библиотека книг"
@ShellComponent
@RequiredArgsConstructor
public class LibraryImpl {

    private final BookDao bookDao;

    @ShellMethod(value = "Add a new book in format:ins bookId authorId genreId bookName", key = {"ins", "insert"})
    public void bookInsert(long bookId, long authorId, long genreId, String bookName) {
        Book b = new Book(bookId, authorId, genreId, bookName);
        try {
            bookDao.insert(b);
            System.out.println("Книга успешно добавлена.");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ShellMethod(value = "Modify the selected book in format:upd bookId authorId genreId bookName", key = {"upd", "update"})
    public void bookUpdate(long bookId, @ShellOption(defaultValue = "-1") long authorId, @ShellOption(defaultValue = "-1") long genreId, @ShellOption(defaultValue = ShellOption.NULL) String bookName) {
        Book b = new Book(bookId, authorId, genreId, bookName);
        try {
            bookDao.update(b);
            System.out.println("Книга успешно изменена.");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ShellMethod(value = "Delete the selected book in format:del bookId", key = {"del", "delete"})
    public void bookDelete(long bookId) {
        try {
            bookDao.deleteById(bookId);
            System.out.println("Книга успешно удалена.");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ShellMethod(value = "Show all the books in the library", key = {"show"})
    public void showAllBooks() {
        try {
            List<Book> lb = bookDao.getAll();
            System.out.println(lb.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
