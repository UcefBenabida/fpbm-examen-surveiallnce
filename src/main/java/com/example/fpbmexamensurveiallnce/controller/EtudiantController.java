package com.example.fpbmexamensurveiallnce.controller;

import com.example.fpbmexamensurveiallnce.entities.EtudiantEntity;
import com.example.fpbmexamensurveiallnce.serviceimplementation.EtudiantEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("etudiants")
public class EtudiantController {

    @Autowired
    EtudiantEntityServiceImpl etudiantEntityService;

    @GetMapping("/getall")
    Iterable<EtudiantEntity> getAll()
    {
        return etudiantEntityService.getAll();
    }

    @PostMapping("/createetudiant")
    EtudiantEntity create(@RequestBody String stringEtudiant)
    {
        return etudiantEntityService.create(stringEtudiant);
    }

}
