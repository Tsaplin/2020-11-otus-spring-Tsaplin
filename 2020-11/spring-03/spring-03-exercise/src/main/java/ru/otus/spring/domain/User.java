package ru.otus.spring.domain;

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
}
