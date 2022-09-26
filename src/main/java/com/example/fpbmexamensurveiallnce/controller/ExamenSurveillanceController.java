package com.example.fpbmexamensurveiallnce.controller;

import com.example.fpbmexamensurveiallnce.entities.EtudiantEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenSurveillanceEntity;
import com.example.fpbmexamensurveiallnce.serviceimplementation.EtudiantEntityServiceImpl;
import com.example.fpbmexamensurveiallnce.serviceimplementation.ExamenEntitySeviceImpl;
import com.example.fpbmexamensurveiallnce.serviceimplementation.ExamenSurveillanceServiceImpl;
import com.example.fpbmexamensurveiallnce.surveillanceclasses.EtudiantSurveillance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("examensurveillance/{token}")
public class ExamenSurveillanceController {

    @Autowired
    ExamenSurveillanceServiceImpl examenSurveillanceServiceImpl ;
    @Autowired
    EtudiantEntityServiceImpl etudiantEntityService;
    @Autowired
    ExamenEntitySeviceImpl examenEntitySevice;

    @GetMapping("getallexamensurveillances")
    String getAll(@PathVariable String token)
    {
        return examenSurveillanceServiceImpl.findAllByTokenInJson(token);
    }
    @GetMapping("/getexamenetudiants")
    String getExamenEtudiants(@PathVariable String token)
    {
        return examenEntitySevice.getExamenEtudiantsInJson(token);
    }

    @GetMapping("scanneetudiantcarte/{etudiant_code_appoge}")
    String scanneEtudiantCarte(@PathVariable String token, @PathVariable String etudiant_code_appoge)
    {
        return  etudiantEntityService.scanneEtudiantCarte(token, etudiant_code_appoge);
    }

    @GetMapping("/scanneexamenpaper/{paper_token}")
    String scanneExamenPaper(@PathVariable String paper_token)
    {
        return examenSurveillanceServiceImpl.scannePaper(paper_token);
    }

    @GetMapping("/validatetoken")
    String validateExamenToken(@PathVariable String token)
    {
        return examenEntitySevice.validateToken(token);
    }

    @GetMapping("/getexamen")
    ExamenEntity getExamenByToken(@PathVariable String token)
    {
        return examenEntitySevice.findByToken(token);
    }

}
