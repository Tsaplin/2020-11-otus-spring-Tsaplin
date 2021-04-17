package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

//@EnableCircuitBreaker
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception  {
        // ! Без запуска SpringApplication.run база данных вообще не создастся
        SpringApplication.run(Main.class, args);
    }

}
