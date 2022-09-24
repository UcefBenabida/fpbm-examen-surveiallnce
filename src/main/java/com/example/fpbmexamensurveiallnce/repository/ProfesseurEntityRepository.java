package com.example.fpbmexamensurveiallnce.repository;
import com.example.fpbmexamensurveiallnce.entities.ProfesseurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("professeurRepository")
public interface ProfesseurEntityRepository extends JpaRepository<ProfesseurEntity, Long> {
}