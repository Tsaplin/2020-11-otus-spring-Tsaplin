package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.ClientOrder;

@Service("orderPrint")
public class OrderPrint {
    public ClientOrder process(ClientOrder order) {
        System.out.println("!!! Order attributes.  number=" + order.getNumber()
                + ", clientFIO=" + order.getClientFIO()
                + ", age=" + order.getAge()
                + ", status=" + order.getStatus());
        return order;
    }
}
