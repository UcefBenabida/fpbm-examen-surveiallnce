package com.example.fpbmexamensurveiallnce.repository;
import com.example.fpbmexamensurveiallnce.entities.FiliereEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("filiereEntityRepository")
public interface FiliereEntityRepository extends JpaRepository<FiliereEntity, Long> {
}