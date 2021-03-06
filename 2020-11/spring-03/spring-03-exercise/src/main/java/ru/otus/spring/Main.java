package ru.otus.spring;

import lombok.var;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.service.Questionnaire;
import ru.otus.spring.service.QuestionnaireImpl;

import java.io.IOException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException  {
        var context = SpringApplication.run(Main.class, args);

        Questionnaire anketaOprosnik = context.getBean(QuestionnaireImpl.class);
        anketaOprosnik.questionnaireExec();

    }

}
