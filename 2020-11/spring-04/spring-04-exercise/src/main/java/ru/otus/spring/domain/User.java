package ru.otus.spring.domain;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

@ShellComponent
@Component
public class User {
    private String name;
    private String surName;

    public String getName() {
        return this.name;
    }

    public String getSurName() {
        return this.surName;
    }

    public void setUser(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    @ShellMethod(value = "Enter your surname, please:", key = {"s", "surname"})
    public String userSurname(String surname) {
        this.surName = surname;
        return String.format("You entered surname: %s", surname);
    }

    @ShellMethod(value = "Enter your name:", key = {"n", "name"})
    public String userName(String name) {
        this.name = name;
        return String.format("You entered name: %s", name);
    }

}
