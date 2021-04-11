package ru.otus.spring.controller;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.service.Library;
import ru.otus.spring.service.LibraryImpl;

@Controller
@AllArgsConstructor
@Import({LibraryImpl.class})
public class BookController {
    private final Library library;
    private static Logger logger = LogManager.getLogger();
    private final Book emptyBook = new Book("0", new Author(0, ""), new Genre(0, ""), "");

    @ModelAttribute
    @GetMapping("/library")
    public Mono<String> libraryShow(@RequestParam(value = "bookId", defaultValue = "0") String bookId, Model model) {
        model.addAttribute("book", emptyBook);
        return Mono.just("library");
    }

    @ModelAttribute
    @GetMapping("/insertBook")
    public Mono<String> prepareForInsert(@RequestParam(value = "bookId", defaultValue = "0") String bookId, Model model) {
        model.addAttribute("authors", library.getAllAuthors());
        model.addAttribute("genres", library.getAllGenres());
        model.addAttribute("book", emptyBook);
        return Mono.just("insertBook");
    }

    @PostMapping("/insertBook")
    public Mono<String> bookInsert(
            @ModelAttribute BookDto book,
            Model model
    ) {
        try {
            library.bookInsert(book.getAuthorId(), book.getGenreId(), book.getName());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("book", emptyBook);
        model.addAttribute("authors", library.getAllAuthors());
        model.addAttribute("genres", library.getAllGenres());

        return Mono.just("insertBook");
    }

    @ModelAttribute
    @GetMapping("/editBook")
    public Mono<String> bookFind(@RequestParam("bookId") String bookId, Model model) {
        Mono<Book> book = library.bookShow(bookId);
        if (book != null) {
            model.addAttribute("book", book.subscribe());
        } else {
            model.addAttribute("book", emptyBook);
        }
        model.addAttribute("authors", library.getAllAuthors());
        model.addAttribute("genres", library.getAllGenres());

        return Mono.just("editBook");
    }

    @PostMapping("/editBook")
    public Mono<String> bookEdit(
            @ModelAttribute BookDto book,
            Model model
    ) {
        try {
            library.bookUpdate(book.getId(), book.getAuthorId(), book.getGenreId(), book.getName());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("book", library.bookShow(book.getId()).subscribe());
        return Mono.just("editBook");
    }

    @ModelAttribute
    @GetMapping("/deleteBook")
    public Mono<String> bookFindForDelete(@RequestParam("bookId") String bookId, Model model) {
        bookFind(bookId, model);
        return Mono.just("deleteBook");
    }

    @PostMapping("/deleteBook")
    public Mono<String> bookDelete(
            @ModelAttribute BookDto book, Model model
    ) {
        try {
            library.bookDelete(book.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("book", emptyBook);
        return Mono.just("deleteBook");
    }

}
