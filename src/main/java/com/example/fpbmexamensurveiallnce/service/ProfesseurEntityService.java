package com.example.fpbmexamensurveiallnce.service;
import com.example.fpbmexamensurveiallnce.entities.ProfesseurEntity;
import org.springframework.stereotype.Service;

@Service("professeurEntityService")
public interface ProfesseurEntityService {

    ProfesseurEntity findById(Long id);
    Iterable<ProfesseurEntity> getAll();

    ProfesseurEntity create(String stringProfessuer);
}
