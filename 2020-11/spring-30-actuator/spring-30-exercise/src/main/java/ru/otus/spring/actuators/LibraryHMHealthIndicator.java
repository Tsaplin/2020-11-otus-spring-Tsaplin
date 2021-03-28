package ru.otus.spring.actuators;
/*
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
*/
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.Library;

import java.util.List;

@Component
@AllArgsConstructor
public class LibraryHMHealthIndicator /* implements HealthIndicator */ {
    private final Library library;
/*
    @Override
    public Health healthCheckBookExist() {
        List<Book> lb = library.showAllBooks();

        if (lb.size() == 0) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Ошибка. В библиотеке нет книг !")
                    .build();
        } else {
            return Health.up().withDetail("message", "В библиотеке есть книги.").build();
        }
    }
*/

}
