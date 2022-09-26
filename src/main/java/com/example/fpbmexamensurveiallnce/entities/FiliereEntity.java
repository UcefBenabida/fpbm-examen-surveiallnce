package com.example.fpbmexamensurveiallnce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "filieres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})

public class FiliereEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "short_name", unique = true)
    private String short_name;

    @Column(name = "code_filiere", unique = true)
    private String code_filiere ;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private List<ModuleEntity> modules;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "filiere_has_etudiants",
            joinColumns = @JoinColumn(name = "filiere_id"),
            inverseJoinColumns = @JoinColumn(name = "etudiant_id")
    )
    private List<EtudiantEntity> etudiants;

    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "filiere_has_chef_professeur",
            joinColumns = @JoinColumn(name = "filiere_id"),
            inverseJoinColumns = @JoinColumn(name = "professeur_id")
    )
    private ProfesseurEntity professeur_chef;


}
