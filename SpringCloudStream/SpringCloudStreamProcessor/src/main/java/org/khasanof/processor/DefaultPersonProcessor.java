package org.khasanof.processor;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.PersonEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nurislom
 * @see org.khasanof.processor
 * @since 5/2/2024 3:50 PM
 */
@Slf4j
@Component
public class DefaultPersonProcessor implements PersonProcessor {

    private final PersonRepository personRepository;

    public DefaultPersonProcessor(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public PersonEvent save(PersonEvent event) {
        Person person = new Person();
        person.setType(event.getType());
        person.setName(event.getName());
        Person saved = personRepository.save(person);
        log.info("person saved : {}", saved);
        return event;
    }
}
