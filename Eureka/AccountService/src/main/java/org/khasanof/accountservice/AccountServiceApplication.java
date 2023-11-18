package org.khasanof.accountservice;

import com.github.javafaker.Faker;
import com.netflix.discovery.EurekaClient;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	Faker faker() {
		return Faker.instance();
	}

}

@RestController
class GreetingController {

	@Lazy
	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private Faker faker;

	@Value("${spring.application.name}")
	private String applicationName;

	@RequestMapping(value = "greeting", method = RequestMethod.GET)
	public String greeting() {
		return String.format("Hell from '%s'!", eurekaClient.getApplication(applicationName));
	}

	@RequestMapping(value = "/account/{name}")
	public ResponseEntity<Account> createAccName(@PathVariable String name) {
		return new ResponseEntity<>(createAcc(name), HttpStatus.OK);
	}

	private Account createAcc(String name) {
		return Account.builder()
				.name(name)
				.company(faker.company().name())
				.ipAddress(faker.internet().ipV4Address())
				.info(faker.lorem().paragraph())
				.build();
	}

}

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Account {

	private String name;
	private String ipAddress;
	private String company;
	private String info;

}
