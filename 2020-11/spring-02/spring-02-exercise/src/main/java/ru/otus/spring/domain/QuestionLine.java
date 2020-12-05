package ru.otus.spring.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionLine implements IQuestionLine {
    private String qText;

    // конструктор
    @Autowired
    public QuestionLine() {

    }

    public void setQtext(String text) {
        this.qText = text;
    }

    public String getQuestionText() {
        return qText;
    }
}
