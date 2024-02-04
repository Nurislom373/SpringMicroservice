# Docker Compose

Docker provides lightweight containers to run services in isolation from our infrastructure so we can deliver software quickly.
In this tutorial, I will show you how to dockerize Spring Boot microservice and Postgres example using Docker Compose.

## Write Docker Compose configurations

```yaml
version: '3.8'

services: 
    postgresdb:
    app:

volumes:
```

* version: Docker Compose file format version will be used.
* services: individual services in isolated containers. Our application has two services: app (Spring Boot) and postgresdb (PostgreSQL database).
* volumes: named volumes that keeps our data alive after restart.

docker-compose.yml example

```yaml
version: "3.8"

services:
  postgresdb:
    image: postgres
    restart: unless-stopped
    env_file: ./.env
    environment:
      - POSTGRES_USER=$POSTGRESDB_USER
      - POSTGRES_PASSWORD=$POSTGRESDB_ROOT_PASSWORD
      - POSTGRES_DB=$POSTGRESDB_DATABASE
    ports:
      - $POSTGRESDB_LOCAL_PORT:$POSTGRESDB_DOCKER_PORT
    volumes:
      - db:/var/lib/postgres
  app:
    depends_on:
      - postgresdb
    build: ./bezkoder-app
    restart: on-failure
    env_file: ./.env
    ports:
      - $SPRING_LOCAL_PORT:$SPRING_DOCKER_PORT
    environment:
      SPRING_APPLICATION_JSON: '{
        "spring.datasource.url"  : "jdbc:postgresql://postgresdb:$POSTGRESDB_DOCKER_PORT/$POSTGRESDB_DATABASE",
        "spring.datasource.username" : "$POSTGRESDB_USER",
        "spring.datasource.password" : "$POSTGRESDB_ROOT_PASSWORD",
        "spring.jpa.properties.hibernate.dialect" : "org.hibernate.dialect.PostgreSQLDialect",
        "spring.jpa.hibernate.ddl-auto" : "update"
      }'
    volumes:
      - .m2:/root/.m2
    stdin_open: true
    tty: true

volumes:
  db:
```

- postgresdb:
  - image: official Docker image
  - restart: configure the restart policy
  - env_file: specify our .env path that we will create later
  - environment: provide setting using environment variables
  - ports: specify ports will be used
  - volumes: map volume folders

- app:
  - depends_on: dependency order, postgresdb is started before app
  - build: configuration options that are applied at build time that we defined in the Dockerfile with relative path
  - environment: environmental variables that Spring Boot application uses
  - stdin_open and tty: keep open the terminal after building container

## Docker Compose Environment variables

In the service configuration, we used environmental variables defined inside the .env file. Now we start writing it.

```
POSTGRESDB_USER=postgres
POSTGRESDB_ROOT_PASSWORD=123456
POSTGRESDB_DATABASE=bezkoder_db
POSTGRESDB_LOCAL_PORT=5433
POSTGRESDB_DOCKER_PORT=5432

SPRING_LOCAL_PORT=6868
SPRING_DOCKER_PORT=8080
```

## Run the Spring Boot microservice with Docker Compose

We can easily run the whole with only a single command:

```docker compose up```

Docker will pull the PostgreSQL and Maven images (if our machine does not have it before).

The services can be run on the background with command:
```docker compose up -d```

## Stop the Application

Stopping all the running containers is also simple with a single command:
```docker compose down```

If you need to stop and remove all containers, networks, and all images used by any service in docker-compose.yml file, use the command:
```docker compose down --rmi all```

## Reference

[DevTo](https://dev.to/tienbku/docker-compose-spring-boot-and-postgres-example-4l82)
[Medium](https://medium.com/@saygiligozde/using-docker-compose-with-spring-boot-and-postgresql-235031106f9f)
[FaunPub](https://faun.pub/docker-compose-setup-and-dockerize-a-spring-boot-application-using-docker-compose-in-linux-mint-d9b70a2ce830)
[Baeldung](https://www.baeldung.com/dockerizing-spring-boot-application)