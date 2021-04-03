package ru.otus.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.Library;

@RestController
@AllArgsConstructor
public class BookAsyncController {
    private final Library library;

    @GetMapping("/all-books")
    public Flux<Book> getAllLibraryBooks() {
        return library.showAllBooks();
    }

}
