package com.example.fpbmexamensurveiallnce.controller;
import com.example.fpbmexamensurveiallnce.entities.ExamenEntity;
import com.example.fpbmexamensurveiallnce.serviceimplementation.ExamenEntitySeviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("examen")
public class ExamenController {

    @Autowired
    ExamenEntitySeviceImpl examenEntitySeviceImpl;

    @PostMapping("/createexamen")
    ExamenEntity createExamen(@RequestBody String examenString)
    {
       return  examenEntitySeviceImpl.createExamen(examenString);
    }

    @GetMapping("getall")
    Iterable<ExamenEntity> getAllExamens()
    {
        return  examenEntitySeviceImpl.getAll();
    }

    @GetMapping("deleteexamen/{id}")
    void deleteExamenById(@PathVariable Long id)
    {
        examenEntitySeviceImpl.dropExamenById(id);
    }

    @GetMapping("/getexamenbyid/{id}")
    String getExamenById(@PathVariable Long id)
    {
        return examenEntitySeviceImpl.findByIdInJson(id);
    }
}
