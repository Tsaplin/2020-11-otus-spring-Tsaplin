package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.ClientOrder;

@Service("orderCheck")
public class OrderCheck {
    public ClientOrder process(ClientOrder order) {
        if (order.getAge() < 18) {
            System.out.println("!!! Treatment is forbidden !");
            order.setStatus("rejected");
        }
        else {
            System.out.println("!!! Successfull treatment of order.");
            order.setStatus("accepted");
        }
        return order;
    }
}
