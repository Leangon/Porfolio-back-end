package com.miporfolio.porfolio.controller;

import com.miporfolio.porfolio.dto.Mensaje;
import com.miporfolio.porfolio.model.Experience;
import com.miporfolio.porfolio.model.Persona;
import com.miporfolio.porfolio.service.impl.IExperienceService;
import com.miporfolio.porfolio.service.impl.IPersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> saveExperience(@RequestBody Experience experience){

        experienceService.saveExperience(experience);
        return new ResponseEntity<>(new Mensaje("Experiencia creada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/experienceDelete/{id}")
    public ResponseEntity<?> deleteExperience(@PathVariable int id){

        experienceService.deleteExperience(id);
        return new ResponseEntity<>(new Mensaje("Experience eliminada"), HttpStatus.OK);
    }

    @GetMapping("/experienceList")
    public ResponseEntity<List<Experience>> listExperience(){

        List<Experience> experiences = experienceService.listExperience();
        return new ResponseEntity<>(experiences, HttpStatus.OK);
    }

    @GetMapping("/experienceFind/{id}")
    public ResponseEntity<Optional<Experience>> findExperience(@PathVariable int id){
        Optional<Experience> experience = experienceService.findExperience(id);
        return new ResponseEntity<>(experience, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/experienceUpdate/{id}")
    public ResponseEntity<?> updateExperience(@Valid @PathVariable int id, @RequestBody Experience experience){
        Optional<Persona> personaOptional = Optional.ofNullable(personaService.findByIdPersona(experience.getPersona().getId()));

        if (personaOptional.isEmpty()){
            return new ResponseEntity<>(new Mensaje("No existe la persona asociada a la experience"), HttpStatus.BAD_REQUEST);
        }

        Optional<Experience> experienceOptional = (experienceService.findExperience(id));

        if (experienceOptional.isEmpty()){
            return new ResponseEntity<>(new Mensaje("Experience que intenta modificar no encontrada"), HttpStatus.BAD_REQUEST);
        }

        experience.setId(experienceOptional.get().getId());
        experience.setPersona(personaOptional.get());

        experienceService.saveExperience(experience);

        return new ResponseEntity<>(new Mensaje("Experience actualizada"), HttpStatus.OK);
    }
}
