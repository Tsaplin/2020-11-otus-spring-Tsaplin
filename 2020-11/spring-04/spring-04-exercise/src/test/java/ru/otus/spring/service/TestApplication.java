package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.Main;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Запуск юнит-тестов")
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
public class TestApplication {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

    //@Autowired
    //private IMyCSVFile myFile;

    @Test
    public void shouldSuccessfullyReadCSVFileTest()
    {
        System.out.println("Запуск юнит-теста shouldSuccessfullyReadCSVFile");
        int actual = 0;

        IMyCSVFile myFile = context.getBean(IMyCSVFile.class);
        try {
            List<IQuestionLine> qList = myFile.readCSVFile();
            System.out.println("Успешное прохождение юнит-теста shouldSuccessfullyReadCSVFile.");
            actual = 1;
        } catch (Exception e) {
            System.out.println("Ошибка юнит-теста shouldSuccessfullyReadCSVFile. " + e.getMessage());
        }

        assertEquals(1, actual);
    }

}
