package org.khasanof.webaccountservice;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class WebAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAccountServiceApplication.class, args);
	}


	@Bean
	@LoadBalanced // Make sure to create the load-balanced template
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	Faker faker() {
		return Faker.instance();
	}

}

@RestController
class SimpleController {


	// Case insensitive: could also use: http://accounts-service
	public static final String ACCOUNTS_SERVICE_URL = "http://ACCOUNT-SERVICE";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Faker faker;

	@Value("${spring.application.name}")
	private String appName;

	@RequestMapping(value = "/call-external-service", method = RequestMethod.GET)
	public Object callExternalService() {
		return restTemplate.getForObject(ACCOUNTS_SERVICE_URL.concat("/account/{name}"), Object.class, faker.name().fullName());
	}

}
