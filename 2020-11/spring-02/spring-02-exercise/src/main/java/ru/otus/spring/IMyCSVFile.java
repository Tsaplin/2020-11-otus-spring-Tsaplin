package ru.otus.spring;

import ru.otus.spring.domain.IQuestionLine;

import java.io.IOException;
import java.util.List;

public interface IMyCSVFile {
    public void setFileName(String fileName);
    public List<IQuestionLine> readCSVFile() throws IOException;

}
