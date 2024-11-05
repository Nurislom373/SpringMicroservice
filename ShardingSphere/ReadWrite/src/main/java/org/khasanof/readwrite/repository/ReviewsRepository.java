package org.khasanof.readwrite.repository;

import org.khasanof.readwrite.domain.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nurislom
 * @see org.khasanof.sharding.repository
 * @since 11/3/2024 11:36 PM
 */
@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
}
