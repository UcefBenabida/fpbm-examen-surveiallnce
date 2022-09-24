package com.example.fpbmexamensurveiallnce.repository;
import com.example.fpbmexamensurveiallnce.entities.ExamenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("examenRepository")
public interface ExamenEntityRepository extends JpaRepository<ExamenEntity, Long> {
    ExamenEntity findExamenEntityByToken(String token);
}