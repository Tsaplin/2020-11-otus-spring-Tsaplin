package ru.otus.spring.domain;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class User {
    private String name;
    private String surName;

    public void setUser(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public String getName() {
        return this.name;
    }

    public String getSurName() {
        return this.surName;
    }

    public User readUserByConsole() {
        Scanner scannerConsole = new Scanner(System.in);

        // Ввод фамилии и имени пользователя
        System.out.print("Введите вашу фамилию: ");
        String userSurname = scannerConsole.nextLine();
        System.out.print("Введите ваше имя: ");
        String userName = scannerConsole.nextLine();
        User user = new User();
        user.setUser(userName, userSurname);

        return user;
    }

}
