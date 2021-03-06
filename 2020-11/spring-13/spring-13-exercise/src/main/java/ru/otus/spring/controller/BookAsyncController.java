package ru.otus.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.Library;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookAsyncController {
    private final Library library;

    @GetMapping("/all-books")
    public List<Book> getAllLibraryBooks() {
        return library.showAllBooks();
    }

}
