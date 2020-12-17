package ru.otus.spring.service;

import org.junit.gen5.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@SpringBootTest
public class TestApplication {

    @Autowired
    private IMyCSVFile myFile;

    @Test
    public void shouldSuccessfullyReadCSVFile()
    {
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        System.out.println("Запуск юнит-теста shouldSuccessfullyReadCSVFile");

        //IMyCSVFile myFile = context.getBean(IMyCSVFile.class);
        try {
            List<IQuestionLine> qList = myFile.readCSVFile();
            System.out.println("Успешное прохождение юнит-теста shouldSuccessfullyReadCSVFile.");
        } catch (Exception e) {
            System.out.println("Ошибка юнит-теста shouldSuccessfullyReadCSVFile. " + e.getMessage());
        }
    }

}
