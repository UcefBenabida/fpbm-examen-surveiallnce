package com.example.fpbmexamensurveiallnce.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "etudiants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class EtudiantEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String first_name;

    @Column(nullable = false, name = "last_name")
    private String last_name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "u-M-d")
    @Column(nullable = false, name = "birth_date")
    private LocalDate birth_date;

    @Column(nullable = false, name = "image")
    private String image;

    @Column(nullable = false, name = "cne")
    private String cne;

    @Column(nullable = false, name = "cin")
    private String cin;

    @Column(nullable = false, name = "code_appoge", unique = true)
    private String codeAppoge;

    @Column(nullable = false, name = "address")
    private String address;

    @Column(nullable = false, name = "phone")
    private String phone;

    @Column(nullable = false, name = "email", unique = true)
    private String email;


    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "filiere_has_etudiants",
            joinColumns = @JoinColumn(name = "etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "filiere_id")
    )
    private FiliereEntity filiere;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "examen_surveillance_has_etudiant",
            joinColumns = @JoinColumn(name = "etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "examen_surveillance_id")
    )
    @ToString.Exclude
    private List<ExamenSurveillanceEntity> examens;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "etudiant_has_modules",
            joinColumns = @JoinColumn(name = "etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id")
    )
    @ToString.Exclude
    private List<ModuleEntity> modules;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;



}
