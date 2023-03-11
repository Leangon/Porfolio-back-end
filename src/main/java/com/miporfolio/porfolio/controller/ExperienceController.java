package com.miporfolio.porfolio.controller;

import com.miporfolio.porfolio.model.Experience;
import com.miporfolio.porfolio.model.Persona;
import com.miporfolio.porfolio.service.impl.IExperienceService;
import com.miporfolio.porfolio.service.impl.IPersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExperienceController {

    @Autowired
    private IExperienceService experienceService;

    @Autowired
    private IPersonaService personaService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/experienceNew")
    public void saveExperience(@RequestBody Experience experience){
        experienceService.saveExperience(experience);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/experienceDelete/{id}")
    public void deleteExperience(@PathVariable int id){
        experienceService.deleteExperience(id);
    }

    @GetMapping("/experienceList")
    public List<Experience> listExperience(){
        return experienceService.listExperience();
    }

    @GetMapping("/experienceFind/{id}")
    public Optional<Experience> findExperience(@PathVariable int id){
        return experienceService.findExperience(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/experienceUpdate/{id}")
    public ResponseEntity<Experience> updateExperience(@Valid @PathVariable int id, @RequestBody Experience experience){
        Optional<Persona> personaOptional = Optional.ofNullable(personaService.findByIdPersona(experience.getPersona().getId()));

        if (personaOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Experience> experienceOptional = (experienceService.findExperience(id));

        if (experienceOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        experience.setId(experienceOptional.get().getId());
        experience.setPersona(personaOptional.get());

        experienceService.saveExperience(experience);

        return ResponseEntity.noContent().build();
    }
}
