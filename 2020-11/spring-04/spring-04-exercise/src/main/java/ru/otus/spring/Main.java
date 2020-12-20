package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.Questionnaire;

import java.io.IOException;

@PropertySource("classpath:Config.properties")
//@ComponentScan
@SpringBootApplication
public class Main {

    // Не потребовался на Java 1.8
    //public static PropertySourcesPlaceholderConfigurer propertyConfig() {
    //    return new PropertySourcesPlaceholderConfigurer();
    //}

    public static void main(String[] args) throws IOException  {
        // TODO: создайте здесь класс контекста
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        System.out.println("First type 'help' to see shell commands. Then enter your name and surname. After it start questionnaire");

        SpringApplication.run(Main.class, args);
        Questionnaire anketaOprosnik = context.getBean(Questionnaire.class);
        anketaOprosnik.QuestionnaireExec();

    }

}
