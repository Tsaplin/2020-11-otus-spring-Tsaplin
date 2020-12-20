package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@PropertySource("classpath:Config.properties")
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException  {
        System.out.println("First type 'help' to see shell commands. Then enter your name and surname. After it start questionnaire");
        SpringApplication.run(Main.class, args);
    }

}
