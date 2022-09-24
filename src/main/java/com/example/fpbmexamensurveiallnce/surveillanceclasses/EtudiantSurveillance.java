package com.example.fpbmexamensurveiallnce.surveillanceclasses;

import com.example.fpbmexamensurveiallnce.entities.EtudiantEntity;
import com.example.fpbmexamensurveiallnce.entities.ExamenEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.ToString;

@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
@JsonSerialize
public class EtudiantSurveillance {
    public EtudiantEntity etudiant;
    public ExamenEntity examen;
    public boolean already_scanned = false;
    public boolean isNotTheSameExamen = false ;
    public String toJason()
    {
        String json = "{ " ;
        if(etudiant != null)
        {
            json += " etudiant: { id: " + etudiant.getId() + ", first_name:" + etudiant.getFirst_name() + ", last_name: " + etudiant.getLast_name() + ", birth_date:" + etudiant.getBirth_date() + ", image:" + etudiant.getImage() + ", cne:" + etudiant.getCne() + ", cin:" + etudiant.getCin() + ", address:" + etudiant.getAddress() + ", code_appoge:" + etudiant.getCodeAppoge() + ", phone:" + etudiant.getPhone() + ", email:" + etudiant.getEmail() + " },  " ;
        }
        if(examen != null)
        {
            json += " examen: { id: " + examen.getId() + ", date: " + examen.getDate() + ", time:" + examen.getTime() + ", local: { id:" + examen.getLocal().getId() + ", name:" + examen.getLocal().getName() + ", capacity:" + examen.getLocal().getCapacity() + " }, module: { id: " + examen.getModule().getId() + ", name: " + examen.getModule().getName() + ", short_name: " + examen.getModule().getShort_name() + " } }, " ;
        }
        json += " already_scanned: " + already_scanned + ", isNotTheSameExamen: " + isNotTheSameExamen + " } ";
        return json;
    }
}
