package com.miporfolio.porfolio.controller;


import com.miporfolio.porfolio.dto.Mensaje;
import com.miporfolio.porfolio.model.Education;
import com.miporfolio.porfolio.model.Persona;
import com.miporfolio.porfolio.service.impl.IEducationService;
import com.miporfolio.porfolio.service.impl.IPersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EducationController {

    @Autowired
    private IEducationService educationService;

    @Autowired
    private IPersonaService personaService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/educationNew")
    public ResponseEntity<?> newEducation(@RequestBody Education education){
        educationService.saveEducation(education);
        return new ResponseEntity<>(new Mensaje("Edu guardada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/educationDelete/{id}")
    public ResponseEntity<?> deleteEducation(@PathVariable int id){

        educationService.deleteEducation(id);
        return new ResponseEntity<>(new Mensaje("Edu eliminada"), HttpStatus.OK);
    }

    @GetMapping("/educationList")
    public ResponseEntity<List<Education>> listEducation(){

        List<Education> educations = educationService.listEducation();
        return new ResponseEntity<>(educations, HttpStatus.OK);
    }

    @GetMapping("/educationFind/{id}")
    public ResponseEntity<Optional<Education>> findEducation(@PathVariable int id){
        Optional<Education> educationOptional = educationService.findExperience(id);
        return new ResponseEntity<>(educationOptional, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/educationUpdate/{id}")
    public ResponseEntity<?> updateEducation(@Valid @PathVariable int id,@RequestBody Education education){
        Optional<Persona> personaOptional = Optional.ofNullable(personaService.findByIdPersona(education.getPersona().getId()));

        if (personaOptional.isEmpty()){
            return new ResponseEntity<>(new Mensaje("La persona asociada a esta edu no existe"), HttpStatus.OK);
        }

        Optional<Education> educationOptional = educationService.findExperience(id);

        if (educationOptional.isEmpty()){
            return new ResponseEntity<>(new Mensaje("La edu que intenta modificar no existe"), HttpStatus.OK);
        }

        education.setId(educationOptional.get().getId());
        education.setPersona(personaOptional.get());

        educationService.saveEducation(education);

        return new ResponseEntity<>(new Mensaje("Edu actualizada"), HttpStatus.OK);
    }

}
