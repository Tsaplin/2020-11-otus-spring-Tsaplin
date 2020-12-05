package ru.otus.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.IQuestionLine;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.QuestionLine;
import ru.otus.spring.service.PersonService;

import java.io.IOException;
import java.util.List;

@ComponentScan
public class Main {

    public static void main(String[] args) throws IOException  {
        // TODO: создайте здесь класс контекста
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        PersonService service = context.getBean(PersonService.class);

        // TODO: Получите Person Service
        // Получите Person "Ivan"
        Person ivan = service.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());

        // Вывод csv-файла
        MyCSVFile myFile = context.getBean(MyCSVFile.class);
        List<QuestionLine> qList = myFile.readCSVFile();
        for (int i=0; i < qList.size(); i++) {
            IQuestionLine qLine = context.getBean(QuestionLine.class);
            qLine = qList.get(i);
            System.out.println(qLine.getQuestionText());
        }

        // Запуск юнит-теста
        testReadCSVFile();

    }

    @Test
    public static void testReadCSVFile()
    {
        MyCSVFile myFile = new MyCSVFile();
        try {
            List<QuestionLine> qList = myFile.readCSVFile();
            System.out.println("Успешное прохождение юнит-теста testReadCSVFile.");
        } catch (Exception e) {
            System.out.println("Ошибка юнит-теста testReadCSVFile. " + e.getMessage());
        }
    }
}
