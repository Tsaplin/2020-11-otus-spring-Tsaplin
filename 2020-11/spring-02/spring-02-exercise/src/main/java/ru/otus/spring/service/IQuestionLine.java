package ru.otus.spring.service;

public interface IQuestionLine {
    String getQuestionText();
    void setQtext(String text);

    boolean getAnswerCorrect();
    void setAnswerCorrect(boolean bool);

    public String getAnswerText();
    void setAnswerText(String text);

    public String getCorrectAnswerText();
    void setCorrectAnswerText(String text);

}
