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
        Console.main(args);
    }

}
