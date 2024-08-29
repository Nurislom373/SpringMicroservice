package org.khasanof;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@Slf4j
@SpringBootApplication
public class SpringAroConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAroConsumerApplication.class, args);
    }

    /**
     * @return
     */
    @Bean
    public Consumer<String> process() {
        return pe -> log.info("Person event: {}", pe);
    }
}
