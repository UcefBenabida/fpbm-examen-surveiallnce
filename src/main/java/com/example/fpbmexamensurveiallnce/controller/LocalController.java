package com.example.fpbmexamensurveiallnce.controller;

import com.example.fpbmexamensurveiallnce.entities.LocalEntity;
import com.example.fpbmexamensurveiallnce.serviceimplementation.LocalEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("locals")
public class LocalController {

    @Autowired
    LocalEntityServiceImpl localEntityService;

    @PostMapping("/createlocal")
    LocalEntity create(@RequestBody String stringLocal)
    {
        return localEntityService.create(stringLocal);
    }

}
