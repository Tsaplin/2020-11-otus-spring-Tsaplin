package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import lombok.var;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.Library;

@RequiredArgsConstructor
@ChangeLog
public class DatabaseChangelog {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final Library library;

    public void dbPrepareData() {

        try (var mongoClient = MongoClients.create()) {
            var db = mongoClient.getDatabase("user_db");
            dropDb(db);
        }

        authorsPrepare(authorDao);
        genresPrepare(genreDao);
        booksPrepare(bookDao, library);
    }

    @ChangeSet(order = "001", id = "dropDb", author = "stsaplin", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "authorsPrepare", author = "stsaplin")
    public void authorsPrepare(AuthorDao authorDao) {
        authorDao.save(new Author(1, "Эдуард Успенский"));
        authorDao.save(new Author(2, "Александр Сергеевич Пушкин"));
        authorDao.save(new Author(3, "Михаил Хазин"));

        System.out.println("Авторы в БД:");
        authorDao.findAll().forEach(p -> System.out.println(p.getFIO()));
        String s = "";
    }

    @ChangeSet(order = "003", id = "genresPrepare", author = "stsaplin")
    public void genresPrepare(GenreDao genreDao) {
        genreDao.save(new Genre(1, "Детские сказки"));
        genreDao.save(new Genre(2, "Экономика"));
        genreDao.save(new Genre(3, "Фантастика"));

        System.out.println("Жанры в БД:");
        genreDao.findAll().forEach(p -> System.out.println(p.getName()));
        String s = "";
    }

    @ChangeSet(order = "004", id = "booksPrepare", author = "stsaplin")
    public void booksPrepare(BookDao bookDao, Library library) {
        bookDao.save(new Book(1, library.getAllAuthors().get(0), library.getAllGenres().get(0), "Тестовая книга."));

        System.out.println("Книги в БД:");
        bookDao.findAll().forEach(p -> System.out.println(p.getName()));
        String s = "";
    }

}
