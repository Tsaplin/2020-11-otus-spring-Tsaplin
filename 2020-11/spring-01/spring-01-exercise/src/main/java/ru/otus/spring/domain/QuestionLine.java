package ru.otus.spring.domain;

import ru.otus.spring.service.IQuestionLine;

public class QuestionLine implements IQuestionLine {
    private final String qText;

    // конструктор
    public QuestionLine(String text) {
        this.qText = text;
    }

    public String getQuestionText() {
        return qText;
    }
}
