package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.ClientOrder;

@Service("orderCheck")
public class OrderCheck {
    public ClientOrder process(ClientOrder order) {
        System.out.println("orderCheck is processing order");
        if (order.age < 18) {
            System.out.println("!!! Treatment is forbidden !");
        }
        else {
            System.out.println("!!! Successfull treatment of order.");
        }
        return order;
    }
}
