package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.io.IOException;

// Класс запуска приложения "библиотека книг"
@ShellComponent
@RequiredArgsConstructor
public class LibraryImpl {

    @ShellMethod(value = "Library:", key = {"l", "library"})
    @ShellMethodAvailability(value = "isUserEntered")
    public void LibraryExec() throws IOException {

    }


}
