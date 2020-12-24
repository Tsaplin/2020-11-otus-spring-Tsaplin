package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:Config.properties")
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception  {
        // ! Без запуска SpringApplication.run база данных вообще не создастся
        ApplicationContext context = SpringApplication.run(Main.class, args);
/*
        Book b = new Book(1, 1, 1, "Test book");

        BookDao bookDao = context.getBean(BookDao.class);
        bookDao.insert(b);

        Book b2 = new Book(1, 1, 1, "Modified Test book");
        bookDao.update(b2);

        Book b3 = new Book(2, 1, 2, "Second book");
        bookDao.insert(b3);

        List<Book> lb = new ArrayList<Book>();
        lb = bookDao.getAll();
        System.out.println(lb.toString());

        bookDao.deleteById(1);
*/
        Console.main(args);
    }

}
