package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component("CustomBookDaoImpl")
public class CustomBookDaoImpl implements CustomBookDao {
    private final BookDao bookDao;

    @Override
    public Optional<Book> customFindById(String bookId) {
        List<Book> lb = bookDao.findAll();
        return lb.stream()
                .filter(x -> x.getBookId().equals(bookId)).findFirst();
    }

}
