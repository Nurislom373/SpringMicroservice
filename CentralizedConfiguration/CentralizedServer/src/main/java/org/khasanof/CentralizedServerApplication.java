package org.khasanof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class CentralizedServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentralizedServerApplication.class, args);
    }

}
