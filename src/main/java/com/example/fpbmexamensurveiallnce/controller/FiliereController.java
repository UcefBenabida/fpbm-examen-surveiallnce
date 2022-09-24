package com.example.fpbmexamensurveiallnce.controller;

import com.example.fpbmexamensurveiallnce.entities.FiliereEntity;
import com.example.fpbmexamensurveiallnce.serviceimplementation.FiliereEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("filieres")
public class FiliereController {

    @Autowired
    private FiliereEntityServiceImpl filiereEntityService;

    @PostMapping("/createfiliere")
    FiliereEntity createFiliere(@RequestBody String filiereString)
    {
        return filiereEntityService.create(filiereString);
    }

    @GetMapping("/getall")
    List<FiliereEntity> getAll()
    {
        return filiereEntityService.getAll();
    }

    @GetMapping("/deletefilierebyid/{id}")
    String deleteFiliereById(@PathVariable Long id)
    {
        filiereEntityService.deleteById(id);
        return "filiere deleted successfully" ;
    }
}
