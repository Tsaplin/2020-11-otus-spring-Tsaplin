package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;
import ru.otus.spring.mongock.changelog.DatabaseChangelogImpl;

@EnableWebFlux
@EnableMongoRepositories
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception  {
        // ! Без запуска SpringApplication.run база данных вообще не создастся
        ApplicationContext context = SpringApplication.run(Main.class, args);
        // Предзаполнение БД
        DatabaseChangelogImpl db_chlog = context.getBean(DatabaseChangelogImpl.class);
        db_chlog.dbPrepareData();
    }

}
