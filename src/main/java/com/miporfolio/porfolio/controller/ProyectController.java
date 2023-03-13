package com.miporfolio.porfolio.controller;

import com.miporfolio.porfolio.model.Persona;
import com.miporfolio.porfolio.model.Proyect;
import com.miporfolio.porfolio.service.impl.IPersonaService;
import com.miporfolio.porfolio.service.impl.IProyectService;
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
public class ProyectController {

    @Autowired
    private IProyectService proyectService;

    @Autowired
    private IPersonaService personaService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/proyectNew")
    public void newProyect(@RequestBody Proyect proyect){
        proyectService.saveProyect(proyect);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/proyectDelete/{id}")
    public void deleteProyect(@PathVariable int id){
        proyectService.deleteProyect(id);
    }

    @GetMapping("/proyectsList")
    public List<Proyect> listProyect(){
        return proyectService.listProyects();
    }

    @GetMapping("/proyectFind/{id}")
    public Optional<Proyect> findProyect(@PathVariable int id){
        return proyectService.findByIdProyect(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/proyectUpdate/{id}")
    public ResponseEntity<Proyect> updateProyect(@Valid @PathVariable int id, @RequestBody Proyect proyect){
        Optional<Persona> personaOptional = Optional.ofNullable(personaService.findByIdPersona(proyect.getPersona().getId()));

        if (personaOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Proyect> proyectOptional = proyectService.findByIdProyect(id);

        if (proyectOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        proyect.setId(proyectOptional.get().getId());
        proyect.setPersona(personaOptional.get());

        proyectService.saveProyect(proyect);

        return ResponseEntity.noContent().build();
    }



}
