package com.example.fpbmexamensurveiallnce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "locals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "capacity")
    int capacity;

    @JsonBackReference
    @OneToMany
    @JoinTable(
            name = "examen_has_local",
            joinColumns = @JoinColumn(name = "local_id"),
            inverseJoinColumns = @JoinColumn(name = "examen_id")
    )
    @ToString.Exclude
    List<ExamenEntity> examens;


}
