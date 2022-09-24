package com.example.fpbmexamensurveiallnce.service;
import com.example.fpbmexamensurveiallnce.entities.LocalEntity;
import org.springframework.stereotype.Service;

@Service("localEntityService")
public interface LocalEntityService {


    LocalEntity create(String stringLocal);


}
