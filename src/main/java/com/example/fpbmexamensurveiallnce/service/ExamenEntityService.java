package com.example.fpbmexamensurveiallnce.service;
import com.example.fpbmexamensurveiallnce.entities.EtudiantEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamenEntityService {

    ExamenEntity createExamen(String examenString);
    Iterable<ExamenEntity> getAll();
    ExamenEntity findById(Long examen_id);
    String findByIdInJson(Long examen_id);

    List<EtudiantEntity> getExamenEtudiants(String token);
    String getExamenEtudiantsInJson(String token);

    void dropExamenById(Long id);
    ExamenEntity findByToken(String token) ;

    String generateUniqueToken();

}
