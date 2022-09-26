package com.example.fpbmexamensurveiallnce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "professeur")
@AllArgsConstructor
@NoArgsConstructor
public class ProfesseurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String first_name;

    @Column(nullable = false, name = "last_name")
    private String last_name;

    @Column(nullable = false, name = "birth_date")
    private LocalDate birth_date;

    @Column(nullable = false, name = "cnp", unique = true)
    private String cne;

    @Column(nullable = false, name = "cin", unique = true)
    private String cin;

    @Column(nullable = false, name = "address")
    private String address;

    @Column(nullable = false, name = "phone", unique = true)
    private String phone;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @JsonManagedReference
    @OneToMany
    @JoinTable(
            name = "professuers_has_module",
            joinColumns = @JoinColumn(name = "professeur_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id")
    )
    private List<ModuleEntity> modules ;
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "examen_has_survaillance_professeurs",
            joinColumns = @JoinColumn(name = "survaillance_professeur_id"),
            inverseJoinColumns = @JoinColumn(name = "examen_id")
    )
    private List<ExamenEntity> examens_survaillanced;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinTable(
            name = "filiere_has_chef_professeur",
            joinColumns = @JoinColumn(name = "professeur_id"),
            inverseJoinColumns = @JoinColumn(name = "filiere_id")
    )
    private FiliereEntity chef_of_filiere;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
