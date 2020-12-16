package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.otus.spring.Main;
import ru.otus.spring.domain.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Класс приложения анкетирования
@Service
@RequiredArgsConstructor
public class QuestionnaireImpl implements Questionnaire {
    private final User user;
    private final IMyCSVFile myFile;
    private IQuestionLine qLine;

    @Override
    public void QuestionnaireExec() throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        // Ввод фамилии и имени пользователя
        User userData = user.readUserByConsole();

        // Вывод csv-файла
        Scanner scannerConsole = new Scanner(System.in);
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

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        IMyCSVFile myFile = context.getBean(IMyCSVFile.class);
        try {
            List<IQuestionLine> qList = myFile.readCSVFile();
            System.out.println("Успешное прохождение юнит-теста testReadCSVFile.");
        } catch (Exception e) {
            System.out.println("Ошибка юнит-теста testReadCSVFile. " + e.getMessage());
        }
    }

}
