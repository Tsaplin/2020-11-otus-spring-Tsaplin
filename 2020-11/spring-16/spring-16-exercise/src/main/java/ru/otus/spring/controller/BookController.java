package ru.otus.spring.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

import java.util.Optional;


@Controller
public class BookController {
    private final BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/editBook")
    public String bookFind(@RequestParam("bookId") long bookId, Model model) {
        Optional<Book> book = bookDao.findById(bookId);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
        }
        return "editBook";
    }

    @PostMapping("/editBook")
    public String bookSave(
            Book book,
            Model model
    ) {
        Book saved = bookDao.save(book);
        model.addAttribute("book", saved);
        return "editBook";
    }


}
