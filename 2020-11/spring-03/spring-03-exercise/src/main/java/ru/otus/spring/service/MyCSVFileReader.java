package ru.otus.spring.service;

import java.io.IOException;
import java.util.List;

public interface MyCSVFileReader {
    void setFileName();
    List<QuestionLine> readCSVFile() throws IOException;
}
