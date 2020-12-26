package ru.otus.spring.service;

import ru.otus.spring.domain.User;

import java.io.IOException;
import java.util.List;

public interface Questionnaire {
    void QuestionnaireExec() throws IOException;

    // Метод подготовки правильных ответов
    void prepareCorrectAnswers(List<IQuestionLine> qList);

    // Метод проверки ответов на правильность
    void checkAnswers(List<IQuestionLine> qList);

    // Метод вывода рез-та тестирования
    boolean showTestingResult(List<IQuestionLine> qList, User user);
}
