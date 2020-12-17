package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.Questionnaire;

import java.io.IOException;

@PropertySource("classpath:Config.properties")
@ComponentScan
public class Main {

    // Не потребовался на Java 1.8
    //public static PropertySourcesPlaceholderConfigurer propertyConfig() {
    //    return new PropertySourcesPlaceholderConfigurer();
    //}

    public static void main(String[] args) throws IOException  {
        // TODO: создайте здесь класс контекста
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        Questionnaire anketaOprosnik = context.getBean(Questionnaire.class);
        anketaOprosnik.QuestionnaireExec();
    }

}
