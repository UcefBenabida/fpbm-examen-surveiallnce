package com.example.fpbmexamensurveiallnce.serviceimplementation;
import com.example.fpbmexamensurveiallnce.entities.EtudiantEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenEntity;
import com.example.fpbmexamensurveiallnce.entities.ModuleEntity;
import com.example.fpbmexamensurveiallnce.entities.ProfesseurEntity;
import com.example.fpbmexamensurveiallnce.repository.ExamenEntityRepository;
import com.example.fpbmexamensurveiallnce.repository.LocalEntityRepository;
import com.example.fpbmexamensurveiallnce.repository.ProfesseurEntityRepository;
import com.example.fpbmexamensurveiallnce.service.ExamenEntityService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service("examenEntityServiceImpl")
public class ExamenEntitySeviceImpl implements ExamenEntityService {

    @Autowired
    ExamenEntityRepository examenEntityRepository;
    @Autowired
    ModuleEntityServiceImpl moduleEntityService ;
    @Autowired
    ProfesseurEntityServiceImpl professeurEntityService;
    @Autowired
    ProfesseurEntityRepository professeurEntityRepository;
    @Autowired
    LocalEntityRepository localEntityRepository;
    @Autowired
    ExamenSurveillanceServiceImpl examenSurveillanceService;

    @Override
    public ExamenEntity createExamen(String examenString) {
        JSONObject jsonObject = new JSONObject(examenString) ;
        String stringDateTime = jsonObject.getString("date") ;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("u-MM-dd");
        LocalDate date = LocalDate.parse(stringDateTime , fmt);
        DateTimeFormatter fmttime = DateTimeFormatter.ofPattern("HH:m");
        LocalTime time = LocalTime.parse(jsonObject.getString("time"), fmttime);
        ModuleEntity module = moduleEntityService.findById(jsonObject.getLong("module_id"));
        List<ProfesseurEntity> profs = new ArrayList<>();
        JSONArray jsonArrayProfesseurs = jsonObject.getJSONArray("professeurs_ids");
        if(jsonArrayProfesseurs.length() > 0)
        {
            for (int i = 0; i < jsonArrayProfesseurs.length() ; i++
            ) {
                ProfesseurEntity professeur1 = professeurEntityRepository.findById(jsonArrayProfesseurs.getLong(i)).get();
                profs.add(professeur1);
            }
        }
        String token = generateUniqueToken();
        ExamenEntity examenEntity = new ExamenEntity(null, token, date, time, jsonObject.getInt("period"),localEntityRepository.findById(jsonObject.getLong("local_id")).get(),module, profs, null,null , null);
        ExamenEntity examen = examenEntityRepository.save(examenEntity) ;
        examenSurveillanceService.createExamenSurveillance(examen);
        return examen;
    }

    @Override
    public Iterable<ExamenEntity> getAll() {
        return examenEntityRepository.findAll();
    }

    @Override
    public ExamenEntity findById(Long examen_id) {
        return examenEntityRepository.findById(examen_id).get();
    }

    @Override
    public String findByIdInJson(Long examen_id) {
        ExamenEntity examen = findById(examen_id);
        return " { id: " + examen.getId() + ", date: " + examen.getDate() + ", time:" + examen.getTime() + ", local: { id:" + examen.getLocal().getId() + ", name:" + examen.getLocal().getName() + ", capacity:" + examen.getLocal().getCapacity() + " }, module: { id: " + examen.getModule().getId() + ", name: " + examen.getModule().getName() + ", short_name: " + examen.getModule().getShort_name() + " } }, " ;
    }

    @Override
    public List<EtudiantEntity> getExamenEtudiants(String token) {

        return examenEntityRepository.findExamenEntityByToken(token).getModule().getEtudiants();
    }

    @Override
    public String getExamenEtudiantsInJson(String token) {
        List<EtudiantEntity> etudiants =  getExamenEtudiants(token);
        String json = "[" ;
        boolean test = false;
        for(EtudiantEntity etudiant: etudiants)
        {
            if(test)
            {
                json += ", ";
            }
            else
            {
                test = true ;
            }
            json += "{ id:" + etudiant.getId() + ", first_name:" + etudiant.getFirst_name() + ", last_name: " + etudiant.getLast_name() + ", birth_date:" + etudiant.getBirth_date() + ", image:" + etudiant.getImage() + ", cne:" + etudiant.getCne() + ", cin:" + etudiant.getCin() + ", address:" + etudiant.getAddress() + ", code_appoge:" + etudiant.getCodeAppoge() + ", phone:" + etudiant.getPhone() + ", email:" + etudiant.getEmail() + " } " ;
        }
        json += "]" ;
        return json;
    }

    @Override
    public ExamenEntity findByToken(String token) {
        return examenEntityRepository.findExamenEntityByToken(token) ;
    }

    @Override
    public String generateUniqueToken() {
        String abcd = "0123456789abcdefghijklmnopqrstuvwxyz" ;
        List<String> letters = Arrays.asList(abcd.split(""));
        Collections. shuffle(letters);
        String shuffled = "";
        for (String letter : letters) {
            shuffled += letter;
        }
        String token = shuffled.substring(0,30) ;
        if(examenEntityRepository.findExamenEntityByToken(token) != null)
        {
            return generateUniqueToken();
        }
        else
        {
            return token ;
        }
    }

    @Override
    public void dropExamenById(Long id) {
        examenEntityRepository.deleteById(id);
    }

    @Override
    public String validateToken(String token) {
        ExamenEntity examen = examenEntityRepository.findExamenEntityByToken(token);
        if(examen != null)
        {
            return "token is valid.";
        }
        else
        {
            return "token is not valid.";
        }
    }
}
