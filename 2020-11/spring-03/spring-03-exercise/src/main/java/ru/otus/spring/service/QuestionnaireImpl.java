package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Класс приложения анкетирования
@RequiredArgsConstructor
@Service
public class QuestionnaireImpl implements Questionnaire {
    private final MyCSVFileReader myFileReader;
    private QuestionLine qLine;
    private final SourceFile sourceFile;

    private User readUserByConsole() {
        Scanner scannerConsole = new Scanner(System.in);

        // Ввод фамилии и имени пользователя
        System.out.print("Введите вашу фамилию: ");
        String userSurname = scannerConsole.nextLine();
        System.out.print("Введите ваше имя: ");
        String userName = scannerConsole.nextLine();
        User user = new User();
        user.setUser(userName, userSurname);

        return user;
    }

    @Override
    public void questionnaireExec() throws IOException {
        // Ввод фамилии и имени пользователя
        User userData = readUserByConsole();

        // Вывод csv-файла
        Scanner scannerConsole = new Scanner(System.in);
        List<QuestionLine> qList = myFileReader.readCSVFile(sourceFile.getFileName());

        prepareCorrectAnswers(qList);

        for (int i=0; i < qList.size(); i++) {
            qLine = qList.get(i);
            System.out.print(qLine.getQuestionText());
            qLine.setAnswerText(scannerConsole.nextLine());
        }
        checkAnswers(qList);
        showTestingResult(qList, userData);
    }

    // Метод подготовки правильных ответов
    @Override
    public void prepareCorrectAnswers(List<QuestionLine> qList) {
        for (int i = 0; i < qList.size(); i++) {
            QuestionLine qLine = qList.get(i);
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
    public void checkAnswers(List<QuestionLine> qList) {
        for (int i = 0; i < qList.size(); i++) {
            QuestionLine qLine = qList.get(i);

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
    public boolean showTestingResult(List<QuestionLine> qList, User user) {
        boolean isWrongAnswerExist = false;

        for (int i = 0; i < qList.size(); i++) {
            QuestionLine qLine = qList.get(i);

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

        return !isWrongAnswerExist;
    }

}
