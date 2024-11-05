package org.khasanof.readwrite.web;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.readwrite.domain.Reviews;
import org.khasanof.readwrite.repository.ReviewsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.sharding.resource.web
 * @since 11/3/2024 11:36 PM
 */
@Slf4j
@RestController
@RequestMapping("/api/reviews")
public class ReviewsResource {

    private final ReviewsRepository reviewsRepository;

    public ReviewsResource(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    /**
     *
     * @param reviews
     * @return
     */
    @PostMapping
    public ResponseEntity<Reviews> createReviews(@RequestBody Reviews reviews) {
        log.debug("REST request to create reviews: {}", reviews);
        return ResponseEntity.ok(reviewsRepository.save(reviews));
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Reviews>> getReviews() {
        return ResponseEntity.ok(reviewsRepository.findAll());
    }
}
