package ru.otus.spring.dao;

import lombok.val;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDaoCustom {
    @Autowired
    BookDao bookDao;

    private static Logger logger = LogManager.getLogger();

    @Override
    public Optional<Book> customFindById(String bookId) {
        Optional<Book> optionalBook = null;
        try {
            optionalBook =  bookDao.findById(bookId);
        } catch (Exception e) {
            logger.info("Книга не найдена по идентификатору id=" + bookId);
        }

        return optionalBook;
    }

}
