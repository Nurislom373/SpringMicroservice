package org.khasanof.dockercompose;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
public class DockerComposeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerComposeApplication.class, args);
    }

}

@Slf4j
@RestController
@RequestMapping(value = "/api")
class PersonController {

    private final PersonRepository personRepository;

    PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping(value = "/person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        log.info("REST request to save Person : {}", person);
        Person saved = personRepository.save(person);
        return ResponseEntity.ok()
                .body(saved);
    }

    @GetMapping(value = "/person")
    public ResponseEntity<List<Person>> findAllPerson() {
        log.info("REST request to get all Person");
        return ResponseEntity.ok(personRepository.findAll());
    }

    @GetMapping(value = "/person/{id}")
    public ResponseEntity<Person> findByPerson(@PathVariable Long id) {
        log.info("REST request to find by id Person");
        return personRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/person/{id}")
    public ResponseEntity<Person> deleteByIdPerson(@PathVariable Long id) {
        log.info("REST request to delete by id Person");
        personRepository.deleteById(id);
        return ResponseEntity.ok()
                .build();
    }

}

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

}

@Repository
interface PersonRepository extends JpaRepository<Person, Long> {
}