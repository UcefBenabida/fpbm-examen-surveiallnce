package com.example.fpbmexamensurveiallnce.controller;

import com.example.fpbmexamensurveiallnce.entities.ModuleEntity;
import com.example.fpbmexamensurveiallnce.serviceimplementation.ModuleEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    ModuleEntityServiceImpl moduleEntityService;

    @PostMapping
    ModuleEntity createModule(String moduleString)
    {
        return moduleEntityService.saveModule(moduleString);
    }

    @GetMapping("/deletemodulebyid/{id}")
    String deleteModuleById(@PathVariable Long id)
    {
        moduleEntityService.deleteModuleById(id);
        return "module deleted successfully" ;
    }
}
