package com.example.fpbmexamensurveiallnce.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "examens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class ExamenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "token", nullable = false, columnDefinition = "varchar(30)", unique = true)
    private String token ;

    @Column(name = "date", nullable = false, columnDefinition = "varchar(11)")
    @JsonFormat(pattern = "u-MM-dd")
    private LocalDate date ;
    @Column(name = "time", nullable = false, columnDefinition = "varchar(10)")
    @JsonFormat(pattern = "HH:m")
    private LocalTime time;

    @Column(name = "period", nullable = false)
    private int period;

    @JsonManagedReference
    @ManyToOne
    @JoinTable(
            name = "examen_has_local",
            joinColumns = @JoinColumn(name = "examen_id"),
            inverseJoinColumns = @JoinColumn(name = "local_id")
    )
    LocalEntity local ;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "examen_has_module",
            joinColumns = @JoinColumn(name = "examen_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id")
    )
    private ModuleEntity module;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "examen_has_survaillance_professeurs",
            joinColumns = @JoinColumn(name = "examen_id"),
            inverseJoinColumns = @JoinColumn(name = "survaillance_professeur_id")
    )
    private List<ProfesseurEntity> survaillance_professeurs;

    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.ALL, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    List<ExamenSurveillanceEntity> examen_survaillances;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;


}
