package com.example.fpbmexamensurveiallnce.serviceimplementation;
import com.example.fpbmexamensurveiallnce.entities.FiliereEntity;
import com.example.fpbmexamensurveiallnce.entities.ModuleEntity;
import com.example.fpbmexamensurveiallnce.entities.ProfesseurEntity;
import com.example.fpbmexamensurveiallnce.repository.FiliereEntityRepository;
import com.example.fpbmexamensurveiallnce.repository.ProfesseurEntityRepository;
import com.example.fpbmexamensurveiallnce.service.FiliereEntityService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service("filiereEntityServiceImpl")
public class FiliereEntityServiceImpl implements FiliereEntityService {

    @Autowired
    private FiliereEntityRepository filiereEntityRepository;
    @Autowired
    private ProfesseurEntityRepository professeurEntityRepository;

    @Override
    public FiliereEntity findById(Long id) {
        return filiereEntityRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        filiereEntityRepository.deleteById(id);
    }

    @Override
    public FiliereEntity create(String filereString) {
        JSONObject jsonObject = new JSONObject(filereString) ;
        String name = jsonObject.getString("name");
        String short_name = jsonObject.getString("short_name");
        String code_filiere = jsonObject.getString("code_filiere");
        ProfesseurEntity professeur_chef = professeurEntityRepository.findById(jsonObject.getLong("professeur_chef_id")).get();
        List<ModuleEntity> modules = new ArrayList<>();
        JSONArray jsonArrayModules = jsonObject.getJSONArray("modules");
        FiliereEntity filiere = filiereEntityRepository.save( new FiliereEntity(null, name, short_name, code_filiere, modules, null, professeur_chef));
        if(jsonArrayModules.length() > 0)
        {
            for (int i = 0; i < jsonArrayModules.length() ; i++
            ) {
                ProfesseurEntity professeur1 = professeurEntityRepository.findById(((JSONObject)jsonArrayModules.get(i)).getLong("professeur_id")).get();
                modules.add(new ModuleEntity(null, ((JSONObject)jsonArrayModules.get(i)).get("name").toString(), ((JSONObject)jsonArrayModules.get(i)).get("short_name").toString(), ((JSONObject)jsonArrayModules.get(i)).get("semestre").toString(),professeur1, filiere, null, null));
            }
        }
        filiere.setModules(modules);
        return filiereEntityRepository.save(filiere);
    }

    @Override
    public List<FiliereEntity> getAll() {
        return filiereEntityRepository.findAll();
    }
}
