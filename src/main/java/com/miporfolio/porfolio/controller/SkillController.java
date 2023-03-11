package com.miporfolio.porfolio.controller;

import com.miporfolio.porfolio.model.Persona;
import com.miporfolio.porfolio.model.Skill;
import com.miporfolio.porfolio.service.impl.IPersonaService;
import com.miporfolio.porfolio.service.impl.ISkillService;
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
public class SkillController {

    @Autowired
    private ISkillService skillService;

    @Autowired
    private IPersonaService personaService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/skillNew")
    public void saveSkill (@RequestBody Skill skill){

        skillService.saveSkill(skill);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/skillDelete/{id}")
    public void deleteSkill(@PathVariable Long id){

        skillService.deleteSkill(id);
    }

    @GetMapping("/skillVerLista")
    @ResponseBody
    public List<Skill> showSkills(){

        return skillService.showSkills();
    }

    @GetMapping("/skillFind/{id}")
    @ResponseBody
    public Skill findSkill(@PathVariable Long id){

        return skillService.findSkill(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/skillUpdate/{id}")
    public ResponseEntity <Skill> updateSkill(@Valid @PathVariable Long id, @RequestBody Skill skill) {
        Optional<Persona> personaOptional = Optional.ofNullable(personaService.findByIdPersona(skill.getPersona().getId()));

        if (personaOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Skill> skillOptional = Optional.ofNullable(skillService.findSkill(id));

        if (skillOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        skill.setId(skillOptional.get().getId());
        skill.setPersona(personaOptional.get());

       skillService.saveSkill(skill);

       return ResponseEntity.noContent().build();
    }
}
