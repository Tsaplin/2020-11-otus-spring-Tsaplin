package ru.otus.spring.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.Library;
import ru.otus.spring.service.LibraryImpl;

import java.util.Optional;

@Controller
@AllArgsConstructor
@Import({LibraryImpl.class})
public class BookController {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final Library library;
    private final Book emptyBook = new Book(0, new Author(), new Genre(), "");

    @GetMapping("/editBook")
    public String bookFind(@RequestParam("bookId") long bookId, Model model) {
        Optional<Book> book = bookDao.findById(bookId);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
        } else {
            model.addAttribute("book", emptyBook);
        }
        model.addAttribute("authors", authorDao.findAll());
        model.addAttribute("genres", genreDao.findAll());

        return "editBook";
    }

    @PostMapping("/editBook")
    public String bookSave(
            Book book,
            Model model
    ) {
        Optional<Book> bookFull = bookDao.findById(book.getBookId());
        bookFull.get().setName(book.getName());
        Book saved = bookDao.save(bookFull.get());
        model.addAttribute("book", saved);
        return "editBook";
    }

    @GetMapping("/deleteBook")
    public String bookFindForDelete(@RequestParam("bookId") long bookId, Model model) {
        bookFind(bookId, model);
        return "deleteBook";
    }

    @PostMapping("/deleteBook")
    public String bookDelete(
            Book book, Model model
    ) {
        try {
            library.bookDelete(book.getBookId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("book", emptyBook);
        return "deleteBook";
    }

}
