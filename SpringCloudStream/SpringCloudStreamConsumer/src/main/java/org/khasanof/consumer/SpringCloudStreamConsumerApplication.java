package org.khasanof.consumer;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.PersonEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@Slf4j
@SpringBootApplication
public class SpringCloudStreamConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamConsumerApplication.class, args);
    }

    /**
     *
     * @return
     */
    @Bean
    public Consumer<PersonEvent> process() {
        return pe -> log.info("Person event: {}", pe);
    }

    @Bean
    public Consumer<String> sink1() {
        return message -> {
           log.info("******************");
           log.info("At Sink1");
           log.info("******************");
           log.info("Received message: {}", message);
        };
    }
}
