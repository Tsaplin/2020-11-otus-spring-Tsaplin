package ru.otus.spring;

import lombok.val;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.dao.BookCommentDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PropertySource("classpath:Config.properties")
@SpringBootApplication
public class Main {
    //private BookCommentDao bcd;

    public static void main(String[] args) throws Exception  {
        // ! Без запуска SpringApplication.run база данных вообще не создастся
        //Book b = new Book(1, 2, 2, "Тестовая книга для комментариев");
        //BookComment bc = new BookComment(1, 1, "Комментарий к тестовой книге");
        //bcd.save();

        ApplicationContext context = SpringApplication.run(Main.class, args);

        BookDao bd = context.getBean(BookDao.class);
        Author a = new Author(1,"Эдуард Успенский");
        Genre g = new Genre(1,"Детские сказки");
        Book b = new Book(0, a, g, "Вторая книга для комментариев");
        bd.save(b);


        System.out.println("1 Кол-во книг=" + bd.count());

        BookCommentDao bcd = context.getBean(BookCommentDao.class);
        List<BookComment> bcl = new ArrayList<BookComment>();
        bcl = bcd.getAll();
        System.out.println(bcl.toString());

        val optionalBook = bd.findById(1);
        BookComment bc = new BookComment(0, optionalBook.get(), "qqqq");
        bcd.save(bc);
        bcd.updateNameById(101, "!!!");
        System.out.println("Комментарии=" + bcd.getAll().toString());
        bcd.deleteById(101);

       // bd.deleteById(1);
        System.out.println("2 Кол-во книг=" + bd.count());

        Console.main(args);
    }

}
