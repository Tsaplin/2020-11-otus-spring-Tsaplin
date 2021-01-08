package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring.Main;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@SpringBootTest
public class TestApplication {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

    //@Autowired
    //private IMyCSVFile myFile;

    @Test
    public void shouldSuccessfullyReadCSVFileTest()
    {
        System.out.println("Запуск юнит-теста shouldSuccessfullyReadCSVFile");
        int actual = 0;

        IMyCSVFile myFile = context.getBean(IMyCSVFile.class);
        try {
            List<IQuestionLine> qList = myFile.readCSVFile();
            System.out.println("Успешное прохождение юнит-теста shouldSuccessfullyReadCSVFile.");
            actual = 1;
        } catch (Exception e) {
            System.out.println("Ошибка юнит-теста shouldSuccessfullyReadCSVFile. " + e.getMessage());
        }

        assertEquals(1, actual);
    }

}
