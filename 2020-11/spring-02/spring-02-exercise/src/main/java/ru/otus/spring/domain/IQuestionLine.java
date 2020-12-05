package ru.otus.spring.domain;

import java.util.List;

public interface IQuestionLine {
    String getQuestionText();
    boolean getAnswerCorrect();
    void setAnswerText(String text);

    // Метод подготовки правильных ответов
    void prepareCorrectAnswers(List<QuestionLine> qList);

    // Метод проверки ответов на правильность
    void checkAnswers(List<QuestionLine> qList);

    // Метод вывода рез-та тестирования
    void showTestingResult(List<QuestionLine> qList);

}
