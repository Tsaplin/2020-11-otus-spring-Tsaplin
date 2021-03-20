package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class ClientOrder {
    public String number;
    public String clientFIO;
    public int age;

    public ClientOrder(String number, String clientFIO, int age) {
        this.number = number;
        this.clientFIO = clientFIO;
        this.age = age;
    }
}
