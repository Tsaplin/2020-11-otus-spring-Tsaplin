package ru.otus.spring.domain;

public class QuestionLine {
    private final String qText;

    // конструктор
    public QuestionLine(String text) {
        this.qText = text;
    }

    public String getQuestionText() {
        return qText;
    }
}
