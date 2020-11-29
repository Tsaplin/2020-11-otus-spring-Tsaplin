package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.QuestionLine;
import ru.otus.spring.service.PersonService;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException  {
        // TODO: создайте здесь класс контекста
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        PersonService service = context.getBean(PersonService.class);

        // TODO: Получите Person Service
        // Получите Person "Ivan"
        Person ivan = service.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());

        // Вывод csv-файла
        MyCSVFile myFile = context.getBean(MyCSVFile.class);
        List<QuestionLine> qList = myFile.readCSVFile();
        for (int i=0; i < qList.size(); i++) {
            QuestionLine qLine = context.getBean(QuestionLine.class);
            qLine = qList.get(i);
            System.out.println(qLine.getQuestionText());
        }

    }

}
