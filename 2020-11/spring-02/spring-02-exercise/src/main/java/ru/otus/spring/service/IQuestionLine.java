package ru.otus.spring.service;

import java.util.List;

public interface IQuestionLine {
    String getQuestionText();
    void setQtext(String text);

    boolean getAnswerCorrect();
    void setAnswerCorrect(boolean bool);

    public String getAnswerText();
    void setAnswerText(String text);

    public String getCorrectAnswerText();
    void setCorrectAnswerText(String text);

    // Метод подготовки правильных ответов
    void prepareCorrectAnswers(List<IQuestionLine> qList);

    // Метод проверки ответов на правильность
    void checkAnswers(List<IQuestionLine> qList);

    // Метод вывода рез-та тестирования
    void showTestingResult(List<IQuestionLine> qList);

}
