package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDaoCustom {
    @Autowired
    BookDao bookDao;

    @Override
    public Optional<Book> customFindById(String bookId) {
        List<Book> lb = bookDao.findAll();
        return lb.stream()
                .filter(x -> x.getBookId().equals(bookId)).findFirst();
    }

}
