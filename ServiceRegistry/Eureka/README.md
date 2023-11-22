# Eureka

Spring Cloud Netflix loyihasining bir qismi bo'lgan Spring Cloud Eureka microservicelarga o'zlarini service registrydan
ro'yxatdan o'tkazish va boshqa servicelarni discover yani topish imkonini beruvchi registry.

---

Spring Cloud Eureka, part of the Spring Cloud Netflix project, is a service registry that allows microservices to
register themselves and discover other services. In essence, it acts like a phone directory for your microservices,
providing a mechanism for service-to-service discovery and registration.

## Set Up Eureka Server

Let’s start with setting up a Eureka Server. First, you need to add the `spring-cloud-starter-netflix-eureka-server`
dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

Then, you need to enable the Eureka server in your Spring Boot application by adding the `@EnableEurekaServer` annotation
in your main application class:

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

Lastly, in your application.yml file, you need to define the properties for Eureka Server:

```yaml
spring:
  application:
    name: account-service

server:
  port: 8081

eureka:
  client:
    serviceUrl:
      defaultZone: ${EUREKA_URI:http://localhost:8761/eureka}
  instance:
    preferIpAddress: true
```
