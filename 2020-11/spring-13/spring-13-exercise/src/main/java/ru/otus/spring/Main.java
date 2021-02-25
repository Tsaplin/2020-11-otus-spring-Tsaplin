package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.mongock.changelog.DatabaseChangelog;
import ru.otus.spring.service.Library;

@EnableMongoRepositories
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception  {
        // ! Без запуска SpringApplication.run база данных вообще не создастся
        ApplicationContext context = SpringApplication.run(Main.class, args);
        GenreDao genreDao = context.getBean(GenreDao.class);
        AuthorDao authorDao = context.getBean(AuthorDao.class);
        BookDao bookDao = context.getBean(BookDao.class);
        Library library = context.getBean(Library.class);
        // Предзаполнение БД
        DatabaseChangelog db_chlog = new DatabaseChangelog(bookDao, authorDao, genreDao, library);
        db_chlog.dbPrepareData();
/*
        System.out.println("Жанры в БД:");
        genreDao.findAll().forEach(p -> System.out.println(p.getName()));

        System.out.println("Авторы в БД:");
        authorDao.findAll().forEach(p -> System.out.println(p.getFIO()));

        System.out.println("Книги в БД:");
        bookDao.findAll().forEach(p -> System.out.println(p.getName()));
*/
    }

}
