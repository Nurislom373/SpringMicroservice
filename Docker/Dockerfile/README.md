# Dockerfile

Dockerizing a Spring Boot application is an essential skill for software developers, as it enables the efficient deployment and management of applications in a containerized environment.

Packaging your Spring Boot app into a Docker container can ensure consistent behavior across different platforms, simplify scaling, and optimize development.

This guide will walk you through the steps to dockerize a Spring Boot application, including creating a Dockerfile, configuring the necessary settings, and building and running the Docker container. Following these steps can enhance your Spring Boot application's performance and maintainability.

```dockerfile
FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/spring-boot-docker.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

- FROM creates a layer from an existing Spring Boot Docker image that exists locally or in any container registry. openjdk:11 will be the one to use.
- VOLUME creates a specific space to persist some data in your container. The tmp folder will store information.
- EXPOSE informs Docker that the container listens to the specified network ports at runtime. This is the port to access the Spring Boot container and will be used to run the container.
- ARG defines a variable that can be passed to the application at runtime. For example, we pass the location of the final jar file within the target folder and save it in a JAR_FILE variable. You can also pass more arguments like credentials, keys, and environment variables with their respective values.
- ADD copies new files, directories or remote file URLs from the source and adds them to the filesystem of the image at the provided path. In our case we add the Spring Boot application to the Docker image from the source path (the JAR_FILE variable) to a destination named app.jar.
- ENTRYPOINT specifies the command that Docker will use to run our app. In this case it will pass the common command to run a jar — java -jar <name of the jar> — so in this case it is java -jar app.jar to our ENTRYPOINT option (remember that we renamed the spring-boot-docker.jar file to app.jar).

## Reference

[Spring Boot Docker](https://spring.io/guides/topicals/spring-boot-docker/)
[Curl](https://curl.se/docs/tutorial.html)