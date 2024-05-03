package org.khasanof.processor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nurislom
 * @see org.khasanof.processor
 * @since 5/2/2024 3:46 PM
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
