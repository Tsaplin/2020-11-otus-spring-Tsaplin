package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.Library;

import java.util.List;

@RequiredArgsConstructor
@ChangeLog
@Component
public class DatabaseChangelogImpl implements DatabaseChangelog {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final Library library;

    @Override
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
    }

    @ChangeSet(order = "003", id = "genresPrepare", author = "stsaplin")
    public void genresPrepare(GenreDao genreDao) {
        genreDao.save(new Genre(1, "Детские сказки"));
        genreDao.save(new Genre(2, "Экономика"));
        genreDao.save(new Genre(3, "Фантастика"));
    }

    @ChangeSet(order = "004", id = "booksPrepare", author = "stsaplin")
    public void booksPrepare(BookDao bookDao, Library library) {
       // List<Author> a = library.getAllAuthors().collectList().block();
       // bookDao.save(new Book(null, library.getAllAuthors().elementAt(0).block(), library.getAllGenres().elementAt(0).block(), "Тестовая книга."));
    }

}
