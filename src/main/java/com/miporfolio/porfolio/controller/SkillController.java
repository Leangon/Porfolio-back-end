package com.miporfolio.porfolio.controller;

import com.miporfolio.porfolio.dto.Mensaje;
import com.miporfolio.porfolio.model.Persona;
import com.miporfolio.porfolio.model.Skill;
import com.miporfolio.porfolio.service.impl.IPersonaService;
import com.miporfolio.porfolio.service.impl.ISkillService;
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
public class SkillController {

    @Autowired
    private ISkillService skillService;

    @Autowired
    private IPersonaService personaService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/skillNew")
    public ResponseEntity<?> saveSkill (@RequestBody Skill skill){

        skillService.saveSkill(skill);
        return new ResponseEntity<>(new Mensaje("Skill creada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/skillDelete/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id){

        skillService.deleteSkill(id);
        return new ResponseEntity<>(new Mensaje("Skill eliminada"), HttpStatus.OK);
    }

    @GetMapping("/skillVerLista")
    @ResponseBody
    public ResponseEntity<List<Skill>> showSkills(){

        List<Skill> skills = skillService.showSkills();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @GetMapping("/skillFind/{id}")
    @ResponseBody
    public ResponseEntity<Skill> findSkill(@PathVariable Long id){

        Skill skill = skillService.findSkill(id);
        return new ResponseEntity<Skill>(skill, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/skillUpdate/{id}")
    public ResponseEntity <?> updateSkill(@Valid @PathVariable Long id, @RequestBody Skill skill) {
        Optional<Persona> personaOptional = Optional.ofNullable(personaService.findByIdPersona(skill.getPersona().getId()));

        if (personaOptional.isEmpty()){
            //return ResponseEntity.unprocessableEntity().build();
            return new ResponseEntity<>(new Mensaje("No existe la persona asociada a esta skill"), HttpStatus.BAD_REQUEST);
        }

        Optional<Skill> skillOptional = Optional.ofNullable(skillService.findSkill(id));

        if (skillOptional.isEmpty()){
            return new ResponseEntity<>(new Mensaje("No existe la skill que intenta modificar"), HttpStatus.BAD_REQUEST);
        }

        skill.setId(skillOptional.get().getId());
        skill.setPersona(personaOptional.get());

       skillService.saveSkill(skill);

       //return ResponseEntity.noContent().build();
        return new ResponseEntity<>(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }
}
