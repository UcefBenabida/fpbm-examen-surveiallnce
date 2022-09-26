package com.example.fpbmexamensurveiallnce.service;
import com.example.fpbmexamensurveiallnce.entities.EtudiantEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenSurveillanceEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ExamenSurveillanceService {

    ExamenSurveillanceEntity findByExamenAndEtudiant(ExamenEntity examen, EtudiantEntity etudiant);
    ExamenSurveillanceEntity save(ExamenSurveillanceEntity examenSurveillanceEntity);
    List<ExamenSurveillanceEntity> createExamenSurveillance(ExamenEntity examen);
    String scannePaper(String token);
    String generateUniqueToken();
    List<ExamenSurveillanceEntity> findAllByToken(String token);
    String findAllByTokenInJson(String token);
    String examenSurveillanceParseJson(ExamenSurveillanceEntity examenSurveillanceEntity);


}
