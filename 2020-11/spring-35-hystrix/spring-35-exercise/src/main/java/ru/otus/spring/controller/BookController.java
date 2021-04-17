package ru.otus.spring.controller;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.Library;
import ru.otus.spring.service.LibraryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BookController {
    private final Library library;
    private static Logger logger = LogManager.getLogger();
    private final Book emptyBook = new Book(0, new Author(0, ""), new Genre(0, ""), "");

    @GetMapping("/library")
    public String libraryShow(@RequestParam(value = "bookId", defaultValue = "0") long bookId, Model model) {
        model.addAttribute("book", emptyBook);

        List<Book> bookList = new ArrayList<>();
        try {
            bookList = library.showAllBooks();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("bookList", bookList);

        return "library";
    }

    @GetMapping("/insertBook")
    public String prepareForInsert(@RequestParam(value = "bookId", defaultValue = "0") long bookId, Model model) {
        model.addAttribute("authors", library.getAllAuthors());
        model.addAttribute("genres", library.getAllGenres());
        model.addAttribute("book", emptyBook);
        return "insertBook";
    }

    @PostMapping("/insertBook")
    public String bookInsert(
            Book book,
            Model model
    ) {
        try {
            library.bookInsert(book.getAuthor().getAuthorId(), book.getGenre().getGenreId(), book.getName());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("book", emptyBook);
        model.addAttribute("authors", library.getAllAuthors());
        model.addAttribute("genres", library.getAllGenres());

        return "insertBook";
    }

    @GetMapping("/editBook")
    public String bookFind(@RequestParam("bookId") long bookId, Model model) {
        Optional<Book> book = library.bookShow(bookId);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
        } else {
            model.addAttribute("book", emptyBook);
        }
        model.addAttribute("authors", library.getAllAuthors());
        model.addAttribute("genres", library.getAllGenres());

        return "editBook";
    }

    @PostMapping("/editBook")
    public String bookEdit(
            Book book,
            Model model
    ) {
        try {
            library.bookUpdate(book.getBookId(), book.getAuthor().getAuthorId(), book.getGenre().getGenreId(), book.getName());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("book", library.bookShow(book.getBookId()).get());
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
