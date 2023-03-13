package com.miporfolio.porfolio.controller;


import com.miporfolio.porfolio.model.Education;
import com.miporfolio.porfolio.model.Persona;
import com.miporfolio.porfolio.service.impl.IEducationService;
import com.miporfolio.porfolio.service.impl.IPersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void newEducation(@RequestBody Education education){
        educationService.saveEducation(education);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/educationDelete/{id}")
    public void deleteEducation(@PathVariable int id){
        educationService.deleteEducation(id);
    }

    @GetMapping("/educationList")
    public List<Education> listEducation(){
        return educationService.listEducation();
    }

    @GetMapping("/educationFind/{id}")
    public Optional<Education> findEducation(@PathVariable int id){
        return educationService.findExperience(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/educationUpdate/{id}")
    public ResponseEntity<Education> updateEducation(@Valid @PathVariable int id,@RequestBody Education education){
        Optional<Persona> personaOptional = Optional.ofNullable(personaService.findByIdPersona(education.getPersona().getId()));

        if (personaOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Education> educationOptional = educationService.findExperience(id);

        if (educationOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        education.setId(educationOptional.get().getId());
        education.setPersona(personaOptional.get());

        educationService.saveEducation(education);

        return ResponseEntity.noContent().build();
    }

}
