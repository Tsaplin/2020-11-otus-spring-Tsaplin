package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.ClientOrder;

@Service("orderPrint")
public class OrderPrint {
    public ClientOrder process(ClientOrder order) {
        System.out.println("orderPrint is processing order");
        System.out.println("!!! Order attrubutes.  number=" + order.number + ", clientFIO=" + order.clientFIO + ", age=" + order.age);
        return order;
    }
}
