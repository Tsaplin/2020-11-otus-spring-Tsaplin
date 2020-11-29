package ru.otus.spring;

import org.springframework.beans.BeanUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.QuestionLine;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyCSVFile {
    private String fileName;

    public MyCSVFile(String fName) {
        this.fileName = fName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<QuestionLine> readCSVFile() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        // Получим имя файла из контекста
        PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(MyCSVFile.class, "fileName");
        String csvfilename = this.fileName; // !!! (String)pd.getValue("fileName");

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream resourceStream = loader.getResourceAsStream(csvfilename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream));

        String line = null;
        Scanner scanner = null;
        int index = 0;
        List<QuestionLine> qList = new ArrayList<QuestionLine>();

        while ((line = reader.readLine()) != null) {
            QuestionLine qLine = null;
            scanner = new Scanner(line);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0)
                    qLine = new QuestionLine(data);
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
