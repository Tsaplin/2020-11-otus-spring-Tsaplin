package ru.otus.spring.controller;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.service.Library;
import ru.otus.spring.service.LibraryImpl;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
@AllArgsConstructor
@Import({LibraryImpl.class})
public class BookController {
    private final Library library;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    private static Logger logger = LogManager.getLogger();
    private final Book emptyBook = new Book("0", new Author(0, ""), new Genre(0, ""), "");

    private static class BookMapper implements RowMapper {
        //@Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            String bookId = resultSet.getString("BookID");
            Author author = (Author) resultSet.getObject("author");
            Genre genre = (Genre) resultSet.getObject("genre");
            String bookName = resultSet.getString("Name");
            return new Book(bookId, author, genre, bookName);
        }

        @Override
        public int[] getRowsForPaths(TreePath[] path) {
            return new int[0];
        }
    }

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
            Mono<Author> authorMono = authorDao.findById((long)1/*book.getAuthorId()*/);
            Mono<Genre> genreMono = genreDao.findById((long)1/*book.getGenreId()*/);
            Mono<Book> bookMono = Mono.zip(authorMono, genreMono)
                    .flatMap(monoEntities -> Mono.just(new Book(null, monoEntities.getT1(), monoEntities.getT2(), book.getName())));
            Mono.zip(bookMono, Mono.just(1))
                    .flatMap(monoEntities -> bookDao.save(monoEntities.getT1()));
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
            model.addAttribute("book", book);
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
            Mono<Author> authorMono = authorDao.findById(book.getAuthorId());
            Mono<Genre> genreMono = genreDao.findById(book.getGenreId());
            Mono<Book> bookMono = Mono.zip(authorMono, genreMono)
                    .flatMap(monoEntities -> Mono.just(new Book(book.getId(), monoEntities.getT1(), monoEntities.getT2(), book.getName())));
            Mono.zip(bookMono, Mono.just(1))
                    .flatMap(monoEntities -> bookDao.save(monoEntities.getT1()));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("book", library.bookShow(book.getId()));
        return Mono.just("editBook");
    }

    @ModelAttribute
    @GetMapping("/deleteBook")
    public Mono<String> bookFindForDelete(@RequestParam("bookId") String bookId, Model model) {
        Mono<Book> book = library.bookShow(bookId);
        if (book != null) {
            model.addAttribute("book", book);
        } else {
            model.addAttribute("book", emptyBook);
        }

        model.addAttribute("authors", library.getAllAuthors());
        model.addAttribute("genres", library.getAllGenres());

        return Mono.just("deleteBook");
    }

    @PostMapping("/deleteBook")
    public Mono<String> bookDelete(
            @ModelAttribute BookDto book, Model model
    ) {
        try {
            bookDao.deleteById(book.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("book", emptyBook);
        return Mono.just("deleteBook");
    }

}