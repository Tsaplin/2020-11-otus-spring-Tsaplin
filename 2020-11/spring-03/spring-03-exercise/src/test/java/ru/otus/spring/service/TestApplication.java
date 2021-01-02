package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.Main;
import ru.otus.spring.domain.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Запуск юнит-тестов")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestApplication {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

    @Autowired
    private MyCSVFile myFile;

    @Autowired
    private Questionnaire questionnaire;

    @MockBean
    private IQuestionLine qLine;

    @Test
    @DisplayName("Запуск юнит-теста shouldFail_QuestionnaireTest. Тестируются неверные ответы. Тест проходит, если ответы неверны.")
    public void shouldFail_QuestionnaireTest()
    {
        int actual = 0;
        boolean result = false;

        User user = new User();
        user.setUser("Petr", "Ivanov");

        List<IQuestionLine> qList = new ArrayList<IQuestionLine>();
        for (int i=0; i < 5; i++) {
            IQuestionLine qLine = new QuestionLine();
            qLine.setAnswerText("no");
            qList.add(qLine);
        }
        questionnaire.prepareCorrectAnswers(qList);
        questionnaire.checkAnswers(qList);
        result = questionnaire.showTestingResult(qList, user);
        actual = (result) ? 1 : 0;

        assertEquals(0, actual);
    }

    @Test
    @DisplayName("Запуск теста shouldSuccessfullyReadCSVFileTest. Для успешного прохождения теста должен начитаться список вопросов из файла.")
    public void shouldSuccessfullyReadCSVFileTest()
    {
        int actual = 0;

        try {
            List<IQuestionLine> qList = myFile.readCSVFile();
            if (!qList.isEmpty()) {
                System.out.println("Успешное прохождение теста shouldSuccessfullyReadCSVFileTest.");
                actual = 1;
            }
        } catch (Exception e) {
            System.out.println("Ошибка теста shouldSuccessfullyReadCSVFileTest. " + e.getMessage());
        }

        assertEquals(1, actual);
    }

}
