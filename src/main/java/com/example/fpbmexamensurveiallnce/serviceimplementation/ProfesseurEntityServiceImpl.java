package com.example.fpbmexamensurveiallnce.serviceimplementation;

import com.example.fpbmexamensurveiallnce.entities.ExamenEntity;
import com.example.fpbmexamensurveiallnce.entities.ModuleEntity;
import com.example.fpbmexamensurveiallnce.entities.ProfesseurEntity;
import com.example.fpbmexamensurveiallnce.repository.ProfesseurEntityRepository;
import com.example.fpbmexamensurveiallnce.service.ProfesseurEntityService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service("professeurEntityServiceImpl")
public class ProfesseurEntityServiceImpl implements ProfesseurEntityService {

    @Autowired
    ProfesseurEntityRepository professeurEntityRepository;

    @Override
    public ProfesseurEntity findById(Long id) {
        return professeurEntityRepository.findById(id).get();
    }

    @Override
    public Iterable<ProfesseurEntity> getAll() {
        return professeurEntityRepository.findAll();
    }

    @Override
    public ProfesseurEntity create(String stringProfessuer) {
        JSONObject jsonObject = new JSONObject(stringProfessuer) ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate dateTime = LocalDate.parse(jsonObject.getString("birth_date"), formatter);
        List<ModuleEntity> modules = new ArrayList<>();
        List<ExamenEntity> examens = new ArrayList<>();

        return professeurEntityRepository.save( new ProfesseurEntity(null, jsonObject.getString("first_name"),  jsonObject.getString("last_name"), dateTime, jsonObject.getString("cnp"), jsonObject.getString("cin"), jsonObject.getString("address"), jsonObject.getString("phone"), jsonObject.getString("email"), modules, examens, null, null, null));
    }
}
