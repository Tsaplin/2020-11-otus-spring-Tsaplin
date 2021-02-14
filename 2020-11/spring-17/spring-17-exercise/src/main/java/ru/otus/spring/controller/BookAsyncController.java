package ru.otus.spring.controller;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.Library;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class BookAsyncController {
    private final Library library;
    private static Logger logger = LogManager.getLogger();

    @GetMapping("/all-books")
    public List<Book> getAllLibraryBooks() {
        List<Book> bookList = new ArrayList<Book>();
        try {
            bookList = library.showAllBooks();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return bookList;
    }

}
