package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.ApplicationProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class MyCSVFileImpl implements MyCSVFile {

    private String fileName;
    private final ApplicationProperties props;
    private final MessageSource messageSource;

    public MyCSVFileImpl(ApplicationProperties props, MessageSource messageSource) {
        this.props = props;
        this.messageSource = messageSource;
    }

    @Override
    public void setFileName() {
        this.fileName = messageSource.getMessage("question.filename", null, props.getLocale());
    }

    @Override
    public List<IQuestionLine> readCSVFile() throws IOException {
        setFileName();

        // Начитаем содержимое файла в BufferedReader, а далее каждую строчку обработаем сканнером
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream resourceStream = loader.getResourceAsStream(fileName);
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
