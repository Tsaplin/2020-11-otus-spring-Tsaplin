package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class ClientOrder {
    private final String number;
    private final String clientFIO;
    private final int age;

    public ClientOrder(String number, String clientFIO, int age) {
        this.number = number;
        this.clientFIO = clientFIO;
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public String getClientFIO() {
        return clientFIO;
    }

    public int getAge() {
        return age;
    }

}
