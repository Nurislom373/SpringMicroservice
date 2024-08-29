package org.khasanof.processor;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.processor
 * @since 5/2/2024 3:44 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;
}
