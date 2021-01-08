package ru.otus.spring.service;

import ru.otus.spring.domain.User;

import java.io.IOException;
import java.util.List;

public interface Questionnaire {
    void questionnaireExec() throws IOException;

    // Метод подготовки правильных ответов
    void prepareCorrectAnswers(List<QuestionLine> qList);

    // Метод проверки ответов на правильность
    void checkAnswers(List<QuestionLine> qList);

    // Метод вывода рез-та тестирования
    boolean showTestingResult(List<QuestionLine> qList, User user);
}
