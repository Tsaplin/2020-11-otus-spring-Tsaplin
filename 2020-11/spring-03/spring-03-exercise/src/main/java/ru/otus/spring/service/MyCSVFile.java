package ru.otus.spring.service;

import org.springframework.context.MessageSource;

import java.io.IOException;
import java.util.List;

public interface MyCSVFile {
    void setFileName();
    List<IQuestionLine> readCSVFile() throws IOException;
    MessageSource messageSource();
}
