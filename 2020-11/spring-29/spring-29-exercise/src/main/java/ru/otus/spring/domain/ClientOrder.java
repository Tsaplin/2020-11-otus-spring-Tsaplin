package ru.otus.spring.domain;

// Клиентская заявка
public class ClientOrder {
    private final String number; // Номер заявки
    private final String clientFIO; //ФИО клиента
    private final int age; //возраст клиента
    private String status; //статус заявки

    public ClientOrder(String number, String clientFIO, int age, String status) {
        this.number = number;
        this.clientFIO = clientFIO;
        this.age = age;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
