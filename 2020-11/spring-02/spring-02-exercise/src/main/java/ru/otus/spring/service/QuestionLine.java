package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class QuestionLine implements IQuestionLine {
    private String qText;               // текст вопроса
    private String answerText;          // текст ответа
    private String correctAnswerText;   // текст правильного ответа
    private boolean isAnswerCorrect;    // признак, является ли данный ответ правильным

    // конструктор
    public QuestionLine() {

    }

    public void setQtext(String text) {
        this.qText = text;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public void setCorrectAnswerText(String text) {
        this.correctAnswerText = text;
    }

    public String getCorrectAnswerText() {
        return correctAnswerText;
    }

    public void setAnswerCorrect(boolean bool) {
        this.isAnswerCorrect = bool;
    }

    public String getQuestionText() {
        return qText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public boolean getAnswerCorrect() {
        return isAnswerCorrect;
    }


}
