package com.example.fpbmexamensurveiallnce.serviceimplementation;
import com.example.fpbmexamensurveiallnce.entities.*;
import com.example.fpbmexamensurveiallnce.repository.ModuleEntityRepository;
import com.example.fpbmexamensurveiallnce.service.ModuleEntityService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service("moduleEntityServiceImpl")
public class ModuleEntityServiceImpl implements ModuleEntityService {

    @Autowired
    ProfesseurEntityServiceImpl professeurEntityService ;
    @Autowired
    private ModuleEntityRepository moduleEntityRepository;
    @Autowired
    private FiliereEntityServiceImpl filiereEntityServiceImpl;

    @Override
    public List<ModuleEntity> getAll() {
        return moduleEntityRepository.findAll();
    }

    @Override
    public ModuleEntity findById(Long id) {
        return moduleEntityRepository.findById(id).get();
    }


    @Override
    public void deleteModuleById(Long id) {
        moduleEntityRepository.deleteById(id);
    }

    @Override
    public ModuleEntity saveModule(String moduleString) {
        JSONObject jsonObject = new JSONObject(moduleString) ;
        String stringProfid = jsonObject.getString("professeur_id");
        String stringFiliereId = jsonObject.getString("professeur_id");
        String name = jsonObject.getString("name") ;
        String short_name = jsonObject.getString("short_name") ;
        String semestre = jsonObject.getString("semestre") ;
        ProfesseurEntity professeur = professeurEntityService.findById(Long.parseLong(stringProfid));
        FiliereEntity filiere = filiereEntityServiceImpl.findById(Long.parseLong(stringFiliereId));
        List<ExamenEntity> listExamns = new ArrayList<>();

        ModuleEntity module = new ModuleEntity(null, name, short_name, semestre,professeur, filiere, listExamns, null);
        return moduleEntityRepository.save(module);
    }

    @Override
    public ModuleEntity findModuleEntityByName(String module) {
        return moduleEntityRepository.findModuleEntityByName(module);
    }


}
