package com.example.fpbmexamensurveiallnce.serviceimplementation;
import com.example.fpbmexamensurveiallnce.entities.EtudiantEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenSurveillanceEntity;
import com.example.fpbmexamensurveiallnce.entities.ModuleEntity;
import com.example.fpbmexamensurveiallnce.repository.*;
import com.example.fpbmexamensurveiallnce.service.EtudiantEntityService;
import com.example.fpbmexamensurveiallnce.surveillanceclasses.EtudiantSurveillance;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("etudiantEntityServiceImpl")
public class EtudiantEntityServiceImpl  implements EtudiantEntityService   {

    @Autowired
    private EtudiantEntityRepository etudiantEntityRepository;
    @Autowired
    private ModuleEntityServiceImpl moduleEntityService;
    @Autowired
    private FiliereEntityServiceImpl filiereEntityService;
    @Autowired
    private ExamenEntitySeviceImpl examenEntitySevice;
    @Autowired
    ExamenSurveillanceServiceImpl examenSurveillanceService;

    @Override
    public Iterable<EtudiantEntity> getAll() {
        return etudiantEntityRepository.findAll();
    }

    @Override
    public List<EtudiantEntity> getEtudiantsByModuleName(String module_name) {
        return moduleEntityService.findModuleEntityByName(module_name).getEtudiants();
    }

    @Override
    public EtudiantEntity findById(Long id) {
        return etudiantEntityRepository.findById(id).get();
    }

    @Override
    public EtudiantEntity editEtudiant(EtudiantEntity etudiant) {
        return etudiantEntityRepository.findById(etudiant.getId()).map(x -> {
            return etudiantEntityRepository.save(etudiant);
        }).orElseGet(() -> {
            return etudiantEntityRepository.save(etudiant);
        });
    }

    @Override
    public boolean deleteEtudiant(Long id) {
       if(etudiantEntityRepository.existsById(id))
       {
           etudiantEntityRepository.deleteById(id);
           return true;
       }
       else
       {
           return false;
       }
    }

    @Override
    public EtudiantEntity create(String stringEtudiant) {
        JSONObject jsonObject = new JSONObject(stringEtudiant) ;
        JSONArray jsonModulesArray = jsonObject.getJSONArray("modules_ids");
        List<ModuleEntity> modulesList = new ArrayList<>();
        if(jsonModulesArray.length() > 0)
        {
            for (int i = 0; i < jsonModulesArray.length() ; i++
            ) {
                ModuleEntity module = moduleEntityService.findById(jsonModulesArray.getLong(i));
                modulesList.add(module);
            }
        }
        String stringBirthDate = jsonObject.getString("birth_date") ;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("u-M-d");
        LocalDate dateTime = LocalDate.parse(stringBirthDate , fmt);
        return etudiantEntityRepository.save( new EtudiantEntity(null, jsonObject.getString("first_name"), jsonObject.getString("last_name"), dateTime, jsonObject.getString("image"), jsonObject.getString("cne"), jsonObject.getString("cin"), jsonObject.getString("code_appoge"), jsonObject.getString("address"), jsonObject.getString("phone"), jsonObject.getString("email"), filiereEntityService.findById(jsonObject.getLong("filiere_id")),null, modulesList,  null, null));
    }

    @Override
    public EtudiantEntity findByAppoge(String code_appoge) {
        return etudiantEntityRepository.findEtudiantEntityByCodeAppoge(code_appoge);
    }

    @Override
    public String scanneEtudiantCarte(String token, String code_appoge) {
        ExamenEntity examen = examenEntitySevice.findByToken(token);
        EtudiantEntity etudiant = etudiantEntityRepository.findEtudiantEntityByCodeAppoge(code_appoge);
        EtudiantSurveillance etudiantSurveillance = new EtudiantSurveillance();
        if(!(examen == null || etudiant == null))
        {
            etudiantSurveillance.etudiant = etudiant;
            ExamenSurveillanceEntity examenSurveillanceEntity = examenSurveillanceService.findByExamenAndEtudiant(examen, etudiant);
            if(examenSurveillanceEntity != null)
            {
                etudiantSurveillance.examen = examen ;
                if(Objects.equals(examenSurveillanceEntity.getPresence(), "Abs"))
                {
                    examenSurveillanceEntity.setPresence("P");
                    examenSurveillanceService.save(examenSurveillanceEntity);
                }
                else
                {
                    etudiantSurveillance.already_scanned = true ;
                }
            }
            else
            {
                List<ExamenSurveillanceEntity> etudiant_examens_srves = etudiant.getExamens();
                for(int i=0; i<etudiant_examens_srves.size(); i++)
                {
                    ExamenEntity an_examen  = etudiant_examens_srves.get(i).getExamen() ;
                    if(an_examen.getDate() == examen.getDate() && an_examen.getTime() == examen.getTime())
                    {
                        etudiantSurveillance.examen = an_examen;
                        etudiantSurveillance.isNotTheSameExamen = true ;
                        break;
                    }
                }
            }

        }
        return etudiantSurveillance.toJason() ;
    }

    @Override
    public EtudiantEntity save(EtudiantEntity etudiant) {
        return etudiantEntityRepository.save(etudiant);
    }


}
