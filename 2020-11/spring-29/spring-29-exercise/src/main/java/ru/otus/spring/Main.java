package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import ru.otus.spring.domain.ClientOrder;

@SpringBootApplication
public class Main {
    @Bean
    DirectChannel outputChannel() {
        return new DirectChannel();
    }

    @MessagingGateway
    public interface I {
        @Gateway(requestChannel = "orderFlow.input")
        void process(ClientOrder order);
    }

    @Bean
    public IntegrationFlow orderFlow() {
        return flow -> flow
                .handle("orderPrint", "process")
                .handle("orderCheck", "process")
                .channel("outputChannel");
    }

    public static void main(String[] args) throws Exception  {
        System.out.println("Hello world !");

        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

        DirectChannel outputChannel = ctx.getBean("outputChannel", DirectChannel.class);

        outputChannel.subscribe(x -> System.out.println(x));
        ctx.getBean(I.class)
                .process(new ClientOrder("Ds-461", "Ivanov Petr Nikiforovich", 27));

        ctx.close();
    }

}
