package ru.otus.spring.service;

import java.io.IOException;
import java.util.List;

public interface MyCSVFile {
    void setFileName(String fileName);
    List<IQuestionLine> readCSVFile() throws IOException;

}
