package ru.otus.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.IQuestionLine;
import ru.otus.spring.domain.QuestionLine;
import ru.otus.spring.domain.User;
import ru.otus.spring.service.PersonService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@ComponentScan
public class Main {

    public static void main(String[] args) throws IOException  {
        // TODO: создайте здесь класс контекста
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        PersonService service = context.getBean(PersonService.class);

        // TODO: Получите Person Service
        // Получите Person "Ivan"
        //Person ivan = service.getByName("Ivan");
        //System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());

        // Ввод фамилии и имени пользователя
        User user = context.getBean(User.class);
        user.readUserByConsole();

        // Вывод csv-файла
        Scanner scannerConsole = new Scanner(System.in);
        MyCSVFile myFile = context.getBean(MyCSVFile.class);
        List<IQuestionLine> qList = myFile.readCSVFile();

        IQuestionLine qLine = context.getBean(QuestionLine.class);
        qLine.prepareCorrectAnswers(qList);

        for (int i=0; i < qList.size(); i++) {
            qLine = qList.get(i);
            System.out.print(qLine.getQuestionText());
            qLine.setAnswerText(scannerConsole.nextLine());
        }
        qLine.checkAnswers(qList);
        qLine.showTestingResult(qList);

        // Запуск юнит-теста
        testReadCSVFile();

    }

    @Test
    public static void testReadCSVFile()
    {
        System.out.println("Запуск юнит-теста testReadCSVFile");
        MyCSVFile myFile = new MyCSVFile();
        try {
            List<IQuestionLine> qList = myFile.readCSVFile();
            System.out.println("Успешное прохождение юнит-теста testReadCSVFile.");
        } catch (Exception e) {
            System.out.println("Ошибка юнит-теста testReadCSVFile. " + e.getMessage());
        }
    }
}
