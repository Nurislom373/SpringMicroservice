package org.khasanof.sharding.repository;

import org.khasanof.sharding.domain.Reviews;
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
