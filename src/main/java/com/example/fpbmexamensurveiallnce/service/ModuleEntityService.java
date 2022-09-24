package com.example.fpbmexamensurveiallnce.service;
import com.example.fpbmexamensurveiallnce.entities.ModuleEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("moduleEntityService")

public interface ModuleEntityService {


    List<ModuleEntity> getAll();
    ModuleEntity findById(Long id);
   // ModuleEntity editModule(ModuleEntity etudiant);
    void deleteModuleById(Long id);
    ModuleEntity saveModule(String moduleString);
    ModuleEntity findModuleEntityByName(String module);
}
