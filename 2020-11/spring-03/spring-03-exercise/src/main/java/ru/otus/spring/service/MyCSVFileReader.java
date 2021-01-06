package ru.otus.spring.service;

import java.io.IOException;
import java.util.List;

public interface MyCSVFileReader {
    List<QuestionLine> readCSVFile(String fileName) throws IOException;
}
