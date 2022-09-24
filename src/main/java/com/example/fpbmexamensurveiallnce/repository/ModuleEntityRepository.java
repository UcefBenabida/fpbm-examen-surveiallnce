package com.example.fpbmexamensurveiallnce.repository;
import com.example.fpbmexamensurveiallnce.entities.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("moduleRepository")
public interface ModuleEntityRepository extends JpaRepository<ModuleEntity, Long> {

    ModuleEntity findModuleEntityByName(String module_name);

}