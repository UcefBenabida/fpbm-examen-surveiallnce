package com.example.fpbmexamensurveiallnce.service;
import com.example.fpbmexamensurveiallnce.entities.FiliereEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("filiereRepository")
public interface FiliereEntityService {

    FiliereEntity findById(Long id);

    void deleteById(Long id);

    FiliereEntity create(String filereString);

    List<FiliereEntity> getAll();
}
