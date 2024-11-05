package org.khasanof.readwrite.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.sharding.domain
 * @since 11/3/2024 11:31 PM
 */
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "author")
    private String author;

    @Column(name = "author_telephone")
    private String authorTelephone;

    @Column(name = "course_id")
    private Integer courseId;
}
