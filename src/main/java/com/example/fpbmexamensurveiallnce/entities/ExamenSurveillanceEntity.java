package com.example.fpbmexamensurveiallnce.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "examen_surveillance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamenSurveillanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "presence", nullable = false, columnDefinition = "varchar(3) default 'Abs'")
    private String presence = "Abs" ;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "examen_surveillance_has_etudiant",
            joinColumns = @JoinColumn(name = "examen_surveillance_id"),
            inverseJoinColumns = @JoinColumn(name = "etudiant_id")
    )
    private EtudiantEntity etudiant ;

    @Column(name = "token",unique = true, nullable = false, columnDefinition = "varchar(30)")
    private String token;

    @Column(name = "is_paper_scanned")
    private boolean isPaperScanned=false;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private ExamenEntity examen;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;


}
