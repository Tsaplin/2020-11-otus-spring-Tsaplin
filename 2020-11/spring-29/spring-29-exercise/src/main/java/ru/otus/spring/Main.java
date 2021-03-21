package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import ru.otus.spring.domain.ClientOrder;

import java.util.ArrayList;

@SpringBootApplication
public class Main {
    @Bean
    DirectChannel outputChannel() {
        return new DirectChannel();
    }

    @MessagingGateway
    public interface I {
        @Gateway(requestChannel = "orderFlow.input")
        void process(Message  messageOfOrders);
    }

    @Bean
    public IntegrationFlow orderFlow() {
        return flow -> {
                  flow
                    .split()
                    .handle("orderCheck", "process")
                    .handle("orderPrint", "process")
                    .aggregate()
                    .enrichHeaders(h -> h.header("ADD_HEADER", "TREATED"))
                    .channel("outputChannel");
        };
    }

    public static void main(String[] args) throws Exception {
        ArrayList<ClientOrder> orders = new ArrayList<ClientOrder>();
        orders.add(new ClientOrder("HG-182", "Anna Michailovna", 16, "unknown"));
        orders.add(new ClientOrder("LP-273", "Igor Nikolaevich", 61, "unknown"));
        orders.add(new ClientOrder("MU-122", "Fedor Petrovich", 17, "unknown"));
        orders.add(new ClientOrder("Ds-461", "Ivanov Petr Nikiforovich", 27, "unknown"));

        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

        DirectChannel outputChannel = ctx.getBean("outputChannel", DirectChannel.class);

        outputChannel.subscribe(x -> System.out.println(x));
        Message message = MessageBuilder.withPayload(orders).build();
        ctx.getBean(I.class).process(message);

        ctx.close();
    }

}
