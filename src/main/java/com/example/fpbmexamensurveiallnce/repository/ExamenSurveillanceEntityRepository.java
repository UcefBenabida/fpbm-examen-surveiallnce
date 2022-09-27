package com.example.fpbmexamensurveiallnce.repository;
import com.example.fpbmexamensurveiallnce.entities.EtudiantEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenSurveillanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("examenServeillanceRepository")
public interface ExamenSurveillanceEntityRepository extends JpaRepository<ExamenSurveillanceEntity, Long> {
    ExamenSurveillanceEntity findExamenSurveillanceEntityByExamenAndEtudiant(ExamenEntity examen, EtudiantEntity etudiant);
    ExamenSurveillanceEntity findExamenSurveillanceEntityByToken(String token);
    List<ExamenSurveillanceEntity> findExamenSurveillanceEntitiesByExamenOrderByPresenceAsc(ExamenEntity examen);
}