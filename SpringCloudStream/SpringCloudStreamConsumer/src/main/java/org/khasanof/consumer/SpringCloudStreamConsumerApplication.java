package org.khasanof.consumer;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.PersonEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.function.Consumer;

@Slf4j
@SpringBootApplication
public class SpringCloudStreamConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamConsumerApplication.class, args);
    }

    /**
     * @return
     */
    @Bean
    public Consumer<PersonEvent> process() {
        return pe -> log.info("Person event: {}", pe);
    }

    @Bean
    public Consumer<Message<String>> sink1() {
        return message -> {
            MessageHeaders headers = message.getHeaders();
            System.out.println("headers = " + headers);
            Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
            System.out.println("acknowledgment = " + acknowledgment);
            if (acknowledgment != null) {
                System.out.println("Acknowledgment provided");
                acknowledgment.acknowledge();
            }
            log.info("******************");
            log.info("At Sink1");
            log.info("******************");
            log.info("Received message: {}", message.getPayload());
        };
    }
}
