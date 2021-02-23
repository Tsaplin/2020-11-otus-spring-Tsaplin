package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.Library;

@EnableMongoRepositories
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception  {
        // ! Без запуска SpringApplication.run база данных вообще не создастся
        ApplicationContext context = SpringApplication.run(Main.class, args);

        // Предзаполнение БД
        GenreDao genreDao = context.getBean(GenreDao.class);
        genreDao.save(new Genre(1, "Детские сказки"));
        genreDao.save(new Genre(2, "Экономика"));
        genreDao.save(new Genre(3, "Фантастика"));
        System.out.println("Жанры в БД:");
        genreDao.findAll().forEach(p -> System.out.println(p.getName()));

        AuthorDao authorDao = context.getBean(AuthorDao.class);
        authorDao.save(new Author(1, "Эдуард Успенский"));
        authorDao.save(new Author(2, "Александр Сергеевич Пушкин"));
        authorDao.save(new Author(3, "Михаил Хазин"));
        System.out.println("Авторы в БД:");
        authorDao.findAll().forEach(p -> System.out.println(p.getFIO()));

        BookDao bookDao = context.getBean(BookDao.class);
        Library library = context.getBean(Library.class);
        bookDao.save(new Book(1, library.getAllAuthors().get(0), library.getAllGenres().get(0), "Тестовая книга для комментариев"));
        System.out.println("Книги в БД:");
        bookDao.findAll().forEach(p -> System.out.println(p.getName()));

    }

}
