package org.khasanof.producer;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.PersonEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

@Slf4j
@RestController
@SpringBootApplication
public class SpringCloudStreamProducerApplication {

    public static final String BINDING_NAME = "randomMessage-out-0";
    private final BlockingQueue<PersonEvent> unbounded = new LinkedBlockingQueue<>();

    private final StreamBridge streamBridge;

    public SpringCloudStreamProducerApplication(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamProducerApplication.class, args);
    }

    @RequestMapping(value = "/api/generate", method = RequestMethod.GET)
    public ResponseEntity<String> generate() {
        PersonEvent personEvent = new PersonEvent();
        personEvent.setName("Abror");
        personEvent.setType("Java");
        boolean offer = unbounded.offer(personEvent);
        System.out.println("offer = " + offer);
        return ResponseEntity.ok("Successfully");
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            PersonEvent personEvent = new PersonEvent();
            personEvent.setName("Abror");
            personEvent.setType("Java");
            boolean offer = unbounded.offer(personEvent);
            System.out.println("offer = " + offer);

            for (int i = 1; i <= 10; i++) {
                streamBridge.send(BINDING_NAME, String.valueOf(i));
            }
        };
    }

//    @Bean
//    public Supplier<PersonEvent> eventSupplier() {
//        log.info("Enter eventSupplier !!!");
//        return unbounded::poll;
//    }

//    @Bean
//    public Supplier<String> randomMessage() {
//        log.info("Enter randomMessage !!!");
//        return () -> UUID.randomUUID().toString();
//    }
}
