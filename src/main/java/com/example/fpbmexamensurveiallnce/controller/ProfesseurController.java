package com.example.fpbmexamensurveiallnce.controller;

import com.example.fpbmexamensurveiallnce.entities.ProfesseurEntity;
import com.example.fpbmexamensurveiallnce.serviceimplementation.ProfesseurEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professeurs")
public class ProfesseurController {

    @Autowired
    private ProfesseurEntityServiceImpl professeurEntityService;

    @PostMapping("createprofesseur")
    ProfesseurEntity create(@RequestBody String stringProfesseur)
    {
        return professeurEntityService.create(stringProfesseur);
    }


}
