package com.example.fpbmexamensurveiallnce.serviceimplementation;
import com.example.fpbmexamensurveiallnce.entities.EtudiantEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenSurveillanceEntity;
import com.example.fpbmexamensurveiallnce.repository.ExamenEntityRepository;
import com.example.fpbmexamensurveiallnce.repository.ExamenSurveillanceEntityRepository;
import com.example.fpbmexamensurveiallnce.service.ExamenSurveillanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("examenSurveillanceServiceImpl")
public class ExamenSurveillanceServiceImpl implements ExamenSurveillanceService {

    @Autowired
    ExamenSurveillanceEntityRepository examenSurveillanceEntityRepository;
    @Autowired
    ExamenEntityRepository examenEntityRepository;

    @Override
    public ExamenSurveillanceEntity findByExamenAndEtudiant(ExamenEntity examen, EtudiantEntity etudiant) {
        return  examenSurveillanceEntityRepository.findExamenSurveillanceEntityByExamenAndEtudiant(examen, etudiant);
    }

    @Override
    public ExamenSurveillanceEntity save(ExamenSurveillanceEntity examenSurveillanceEntity) {
        return examenSurveillanceEntityRepository.save(examenSurveillanceEntity);
    }

    @Override
    public List<ExamenSurveillanceEntity> createExamenSurveillance(ExamenEntity examen) {
        List<ExamenSurveillanceEntity> examenSurveillanceEntities = new ArrayList() ;
        List<EtudiantEntity> etudiants = examen.getModule().getEtudiants();
        String token = "" ;
        for (EtudiantEntity etudiant : etudiants) {
            token = generateUniqueToken();
            examenSurveillanceEntities.add(new ExamenSurveillanceEntity(null, "Abs", etudiant, token, false ,examen, null, null));
        }
        return examenSurveillanceEntityRepository.saveAll(examenSurveillanceEntities);
    }

    @Override
    public String scannePaper(String token) {
        ExamenSurveillanceEntity examenSurveillanceEntity = examenSurveillanceEntityRepository.findExamenSurveillanceEntityByToken(token);
        if(examenSurveillanceEntity != null)
        {
            if(Objects.equals(examenSurveillanceEntity.getPresence(), "P"))
            {
                if(examenSurveillanceEntity.isPaperScanned())
                {
                    return "Paper is already scanned." ;
                }
                else
                {
                    examenSurveillanceEntity.setPaperScanned(true);
                    examenSurveillanceEntityRepository.save(examenSurveillanceEntity);
                    return "Paper scanned successfully." ;
                }
            }
            else
            {
                return  "Student is absent." ;
            }
        }
        else
        {
            return "not found row." ;
        }
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
        if(examenSurveillanceEntityRepository.findExamenSurveillanceEntityByToken(token) != null)
        {
            return generateUniqueToken();
        }
        else
        {
            return token ;
        }
    }

    @Override
    public List<ExamenSurveillanceEntity> findAllByToken(String token) {
        return examenSurveillanceEntityRepository.findExamenSurveillanceEntitiesByExamenOrderByPresenceAsc(examenEntityRepository.findExamenEntityByToken(token));
    }

    @Override
    public String findAllByTokenInJson(String token) {
        String json = "[ ";
        boolean test  =false;
        List<ExamenSurveillanceEntity> examenServs = examenSurveillanceEntityRepository.findExamenSurveillanceEntitiesByExamenOrderByPresenceAsc(examenEntityRepository.findExamenEntityByToken(token)) ;
        for (ExamenSurveillanceEntity examenSurveillanceEntity : examenServs)
        {
            if(test)
            {
                json += " , ";
            }
            else
            {
                test = true ;
            }
            json += this.examenSurveillanceParseJson(examenSurveillanceEntity);
        }
        json += " ]" ;
        return json;
    }

    @Override
    public String examenSurveillanceParseJson(ExamenSurveillanceEntity examenSurveillanceEntity) {
        return "{ \"id\": " + examenSurveillanceEntity.getId() + " , \"presence\":\"" + examenSurveillanceEntity.getPresence() + "\" , \"isPaperScanned\": " + examenSurveillanceEntity.isPaperScanned() + " , \"etudiant\": { \"id\": " + examenSurveillanceEntity.getEtudiant().getId() + ", \"first_name\": \"" + examenSurveillanceEntity.getEtudiant().getFirst_name() + "\" , \"last_name\": \"" + examenSurveillanceEntity.getEtudiant().getLast_name() + "\", \"birth_date\": \"" + examenSurveillanceEntity.getEtudiant().getBirth_date() + "\", \"image\": \"" + examenSurveillanceEntity.getEtudiant().getImage() + "\" , \"cne\": \"" + examenSurveillanceEntity.getEtudiant().getCne() + "\" , \"cin\": \"" + examenSurveillanceEntity.getEtudiant().getCin() + "\" , \"address\": \"" + examenSurveillanceEntity.getEtudiant().getAddress() + "\" , \"code_appoge\": \"" + examenSurveillanceEntity.getEtudiant().getCodeAppoge() + "\" , \"phone\": \"" + examenSurveillanceEntity.getEtudiant().getPhone() + "\" , \"email\": \"" + examenSurveillanceEntity.getEtudiant().getEmail() + "\" } }" ;
    }


}
