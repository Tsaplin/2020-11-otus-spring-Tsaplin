package ru.otus.spring.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.otus.spring.Main;

import java.util.Scanner;

@Service
public class User {
    private String name;
    private String surName;

    @Autowired
    public User() {
    }

    public void setUser(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public User readUserByConsole() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Scanner scannerConsole = new Scanner(System.in);

        // Ввод фамилии и имени пользователя
        System.out.print("Введите вашу фамилию: ");
        String userSurname = scannerConsole.nextLine();
        System.out.print("Введите ваше имя: ");
        String userName = scannerConsole.nextLine();
        User user = context.getBean(User.class);
        user.setUser(userName, userSurname);

        return user;
    }

}
