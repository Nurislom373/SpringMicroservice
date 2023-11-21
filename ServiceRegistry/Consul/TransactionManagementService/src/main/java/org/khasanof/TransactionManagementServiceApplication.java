package org.khasanof;

import com.ecwid.consul.v1.ConsulClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class TransactionManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionManagementServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper() {{
            registerModule(new JavaTimeModule());
        }};
    }

}

@Component
class TransactionManagementController implements CommandLineRunner {

    private static final String SERVICE_ID = "TRANSACTION-SERVICE";
    private static final String TRAN_SERVICE = "http://" + SERVICE_ID;
    private static final String FAKE_TRANS_URI = TRAN_SERVICE.concat("/api/trans/3");

    private final ConsulClient consulClient;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ConsulDiscoveryClient discoveryClient;

    TransactionManagementController(ConsulDiscoveryClient discoveryClient, ConsulClient consulClient, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.discoveryClient = discoveryClient;
        this.consulClient = consulClient;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        List<ServiceInstance> list = discoveryClient.getAllInstances();
        System.out.println("print pretty list = " + objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(list));

        List<ServiceInstance> instances = discoveryClient.getInstances(SERVICE_ID);
        System.out.println("instances = " + objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(instances));

        consulClient.setKVValue("test", "value!");

        simpleRequestExample();
    }

    @SneakyThrows
    private void simpleRequestExample() {
        System.out.println("service response = " + objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(restTemplate.getForObject(FAKE_TRANS_URI, Object.class)));
    }

}
