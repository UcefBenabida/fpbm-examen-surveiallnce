package com.example.fpbmexamensurveiallnce.repository;
import com.example.fpbmexamensurveiallnce.entities.EtudiantEntity;
import com.example.fpbmexamensurveiallnce.entities.ModuleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("etudiantEntityRepository")
public interface EtudiantEntityRepository extends CrudRepository<EtudiantEntity, Long> {
    EtudiantEntity findEtudiantEntityByCodeAppoge(String codeAppoge);
}