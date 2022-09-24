package com.example.fpbmexamensurveiallnce.serviceimplementation;
import com.example.fpbmexamensurveiallnce.entities.ExamenEntity;
import com.example.fpbmexamensurveiallnce.entities.LocalEntity;
import com.example.fpbmexamensurveiallnce.repository.LocalEntityRepository;
import com.example.fpbmexamensurveiallnce.service.LocalEntityService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service("localEntityServiceImpl")
public class LocalEntityServiceImpl implements LocalEntityService {

    @Autowired
    LocalEntityRepository localEntityRepository;

    @Override
    public LocalEntity create(String stringLocal) {
        JSONObject jsonObject = new JSONObject(stringLocal);
        List<ExamenEntity> listExamens = new ArrayList();
        return localEntityRepository.save(new LocalEntity(null, jsonObject.getString("name"), jsonObject.getInt("capacity"), listExamens));
    }
}
