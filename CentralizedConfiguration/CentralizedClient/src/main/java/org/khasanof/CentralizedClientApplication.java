package org.khasanof;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CentralizedClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentralizedClientApplication.class, args);
    }
}

@RefreshScope
@RestController
class MessageRestController {

    @Value("${config.message:Hello default}")
    private String message;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String getMessage() {
        return this.message;
    }
}