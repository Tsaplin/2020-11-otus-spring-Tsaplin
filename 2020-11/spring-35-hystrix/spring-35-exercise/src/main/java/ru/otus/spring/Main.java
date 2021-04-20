package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
//import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableCircuitBreaker
//@EnableTurbine
@EnableHystrixDashboard
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // ! Без запуска SpringApplication.run база данных вообще не создастся
        SpringApplication.run(Main.class, args);
    }

}
