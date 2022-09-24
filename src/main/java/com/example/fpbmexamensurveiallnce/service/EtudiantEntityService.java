package com.example.fpbmexamensurveiallnce.service;
import com.example.fpbmexamensurveiallnce.entities.EtudiantEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenEntity;
import com.example.fpbmexamensurveiallnce.surveillanceclasses.EtudiantSurveillance;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface EtudiantEntityService {




    public Iterable<EtudiantEntity> getAll() ;
    List<EtudiantEntity> getEtudiantsByModuleName(String module) ;
    EtudiantEntity findById(Long id);
    EtudiantEntity editEtudiant(EtudiantEntity etudiant);
    boolean deleteEtudiant(Long id);
    EtudiantEntity create(String stringEtudiant);
    EtudiantEntity findByAppoge(String code_appoge);
    String scanneEtudiantCarte(String token, String code_appoge);
    EtudiantEntity save(EtudiantEntity etudiant);

}
