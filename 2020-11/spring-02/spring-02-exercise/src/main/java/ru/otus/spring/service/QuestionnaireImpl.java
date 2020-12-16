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
        // Ввод фамилии и имени пользователя
        User userData = user.readUserByConsole();

        // Вывод csv-файла
        Scanner scannerConsole = new Scanner(System.in);
        List<IQuestionLine> qList = myFile.readCSVFile();

        prepareCorrectAnswers(qList);

        for (int i=0; i < qList.size(); i++) {
            qLine = qList.get(i);
            System.out.print(qLine.getQuestionText());
            qLine.setAnswerText(scannerConsole.nextLine());
        }
        checkAnswers(qList);
        showTestingResult(qList, userData);

        // Запуск юнит-теста
        testReadCSVFile();
    }

    // Метод подготовки правильных ответов
    @Override
    public void prepareCorrectAnswers(List<IQuestionLine> qList) {
        for (int i = 0; i < qList.size(); i++) {
            IQuestionLine qLine = qList.get(i);
            String correctAnswerText = "";
            if (i == 0) {correctAnswerText = "spring";}
            else if (i == 1) {correctAnswerText = "y";}
            else if (i == 2) {correctAnswerText = "y";}
            else if (i == 3) {correctAnswerText = "3";}
            else if (i == 4) {correctAnswerText = "y";}
            else {
                correctAnswerText = "???";
            }
            qLine.setCorrectAnswerText(correctAnswerText);
        }

    }

    // Метод проверки ответов на правильность
    @Override
    public void checkAnswers(List<IQuestionLine> qList) {
        for (int i = 0; i < qList.size(); i++) {
            IQuestionLine qLine = qList.get(i);

            if (qLine.getAnswerText().equals(qLine.getCorrectAnswerText())) {
                qLine.setAnswerCorrect(true);
            }
            else {
                qLine.setAnswerCorrect(false);
            }
        }
    }

    // Метод вывода рез-та тестирования
    @Override
    public void showTestingResult(List<IQuestionLine> qList, User user) {
        boolean isWrongAnswerExist = false;

        for (int i = 0; i < qList.size(); i++) {
            IQuestionLine qLine = qList.get(i);

            if (!qLine.getAnswerCorrect()) {
                isWrongAnswerExist = true;
                break;
            }
        }

        if (isWrongAnswerExist) {
            System.out.println("Тестирование студента " + user.getSurName() + " " + user.getName() + " НЕ пройдено. Есть ошибки.");
        }
        else {
            System.out.println("Тестирование студента " + user.getSurName() + " " + user.getName() + " успешно пройдено.");
        }
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
