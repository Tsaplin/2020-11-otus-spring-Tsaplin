package ru.otus.spring.service;

import lombok.var;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.ApplicationProps;

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
    private final ApplicationProps props;
    private final MessageSource messageSource;

    public MyCSVFileImpl(ApplicationProps props, MessageSource messageSource) {
        this.props = props;
        this.messageSource = messageSource();
    }

    @Override
    public MessageSource messageSource() {
        var ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Override
    public void setFileName() {
        this.fileName = messageSource.getMessage("question.filename", null, props.getLocale());
    }

    @Override
    public List<IQuestionLine> readCSVFile() throws IOException {
        setFileName();
        String csvfilename = fileName;

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
