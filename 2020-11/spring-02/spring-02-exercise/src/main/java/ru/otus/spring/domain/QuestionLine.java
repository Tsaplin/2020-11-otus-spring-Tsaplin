package ru.otus.spring.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class QuestionLine implements IQuestionLine {
    private String qText;               // текст вопроса
    private String answerText;          // текст ответа
    private String correctAnswerText;   // текст правильного ответа
    private boolean isAnswerCorrect;    // признак, является ли данный ответ правильным

    // конструктор
    @Autowired
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

    private void setAnswerCorrect(boolean bool) {
        this.isAnswerCorrect = bool;
    }

    public String getQuestionText() {
        return qText;
    }

    public boolean getAnswerCorrect() {
        return isAnswerCorrect;
    }

    // Метод подготовки правильных ответов
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
    public void checkAnswers(List<QuestionLine> qList) {
        for (int i = 0; i < qList.size(); i++) {
            QuestionLine qLine = qList.get(i);

            if (qLine.answerText.equals(qLine.correctAnswerText)) {
                qLine.setAnswerCorrect(true);
            }
            else {
                qLine.setAnswerCorrect(false);
            }
        }
    }

    // Метод вывода рез-та тестирования
    public void showTestingResult(List<QuestionLine> qList) {
        boolean isWrongAnswerExist = false;

        for (int i = 0; i < qList.size(); i++) {
            QuestionLine qLine = qList.get(i);

            if (!qLine.getAnswerCorrect()) {
                isWrongAnswerExist = true;
                break;
            }
        }

        if (isWrongAnswerExist) {
            System.out.println("Тестирование студента НЕ пройдено. Есть ошибки.");
        }
        else {
            System.out.println("Тестирование студента успешно пройдено.");
        }
    }

}
