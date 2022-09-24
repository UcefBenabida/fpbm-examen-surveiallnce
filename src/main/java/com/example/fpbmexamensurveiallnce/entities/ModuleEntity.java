package com.example.fpbmexamensurveiallnce.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "module")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "short_name", unique = true)
    private String short_name;

    @Column(name = "semestre", unique = true)
    private String semestre;

    @ManyToOne
    @JsonBackReference
    @JoinTable(
            name = "professuers_has_module",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "professeur_id")
    )
    ProfesseurEntity professeur;

    @JsonBackReference
    @ManyToOne
    private FiliereEntity filiere;

    @JsonBackReference
    @OneToMany
    @JoinTable(
            name = "examen_has_module",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "examen_id")
    )
    @ToString.Exclude
    List<ExamenEntity> examens;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "etudiant_has_modules",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "etudiant_id")
    )
    @ToString.Exclude
    private List<EtudiantEntity> etudiants;


}
