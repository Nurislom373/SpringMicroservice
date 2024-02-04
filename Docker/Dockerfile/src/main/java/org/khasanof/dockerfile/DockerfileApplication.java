package org.khasanof.dockerfile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DockerfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerfileApplication.class, args);
    }

}

@Slf4j
@RestController
@RequestMapping("/api")
class TestController {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public ResponseEntity<String> greeting() {
        log.info("REQUEST to greeting!");
        return ResponseEntity.ok("Hello World!");
    }

}
