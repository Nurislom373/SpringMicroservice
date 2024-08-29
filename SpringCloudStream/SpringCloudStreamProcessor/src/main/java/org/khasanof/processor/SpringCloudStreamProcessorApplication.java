package org.khasanof.processor;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.PersonEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.DefaultAfterRollbackProcessor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.backoff.FixedBackOff;

import java.util.function.Function;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class SpringCloudStreamProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamProcessorApplication.class, args);
    }

    @Bean
    public ListenerContainerCustomizer<AbstractMessageListenerContainer<byte[], byte[]>> customizer() {
        // Disable retry in the AfterRollbackProcessor
        return (container, destination, group) -> container.setAfterRollbackProcessor(
                new DefaultAfterRollbackProcessor<>((record, exception) -> System.out.println("Discarding failed record: " + record),
                        new FixedBackOff(0L, 0)));
    }

    @Bean
    public Function<PersonEvent, PersonEvent> process(PersonProcessor processor) {
        return personEvent -> {
            log.info("Received event: {}", personEvent);
            return processor.save(personEvent);
        };
    }

    @Bean
    public Function<String, String> sink() {
        return s -> {
            log.info("Received event: {}", s);
            return s;
        };
    }
}
