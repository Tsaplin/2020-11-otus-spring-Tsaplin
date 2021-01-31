package ru.otus.spring.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookDao bookDao;

    @GetMapping("/book/edit")
    public String editPage(@RequestParam("bookId") long bookId, Model model) {
        Optional<Book> book = bookDao.findById(bookId); //.orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/book/save")
    public String savePerson(
            Book book,
            Model model
    ) {
        Book saved = bookDao.save(book);
        model.addAttribute(saved);
        return "saveBook";
    }


}
