package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.IQuestionLine;
import ru.otus.spring.domain.QuestionLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class MyCSVFile {
    private String fileName = "Questions.csv";

/*
    public MyCSVFile(String fName) {
        this.fileName = fName;
    }
*/
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<IQuestionLine> readCSVFile() throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        // Получим имя файла из контекста
        MyCSVFile myFile = context.getBean(MyCSVFile.class);
        String csvfilename = myFile.fileName;

        // Начитаем содержимое файла в BufferedReader, а далее каждую строчку обработаем сканнером
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream resourceStream = loader.getResourceAsStream(csvfilename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream));

        String line = null;
        Scanner scanner = null;
        int index = 0;
        List<IQuestionLine> qList = new ArrayList<IQuestionLine>();

        while ((line = reader.readLine()) != null) {
            QuestionLine qLine = null;
            scanner = new Scanner(line);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0) {
                    qLine = new QuestionLine();
                    qLine.setQtext(data);
                }
                else
                    System.out.println("Некорректные данные:" + data);
                index++;
            }
            index = 0;
            qList.add(qLine);
        }
        reader.close();

        return qList;
    }
}
