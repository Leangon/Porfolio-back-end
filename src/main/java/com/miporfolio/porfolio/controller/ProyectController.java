package com.miporfolio.porfolio.controller;

import com.miporfolio.porfolio.dto.Mensaje;
import com.miporfolio.porfolio.model.Persona;
import com.miporfolio.porfolio.model.Proyect;
import com.miporfolio.porfolio.service.impl.IPersonaService;
import com.miporfolio.porfolio.service.impl.IProyectService;
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
public class ProyectController {

    @Autowired
    private IProyectService proyectService;

    @Autowired
    private IPersonaService personaService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/proyectNew")
    public ResponseEntity<?> newProyect(@RequestBody Proyect proyect){

        proyectService.saveProyect(proyect);
        return new ResponseEntity<>(new Mensaje("Proyect agregado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/proyectDelete/{id}")
    public ResponseEntity<?> deleteProyect(@PathVariable int id){

        proyectService.deleteProyect(id);
        return new ResponseEntity<>(new Mensaje("Proyect eliminado"), HttpStatus.OK);
    }

    @GetMapping("/proyectsList")
    public ResponseEntity<List<Proyect>> listProyect(){

        List<Proyect> proyects = proyectService.listProyects();
        return new ResponseEntity<>(proyects, HttpStatus.OK);
    }

    @GetMapping("/proyectFind/{id}")
    public ResponseEntity<Optional<Proyect>> findProyect(@PathVariable int id){
        Optional<Proyect> proyectOptional = proyectService.findByIdProyect(id);
        return new ResponseEntity<>(proyectOptional, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/proyectUpdate/{id}")
    public ResponseEntity<?> updateProyect(@Valid @PathVariable int id, @RequestBody Proyect proyect){
        Optional<Persona> personaOptional = Optional.ofNullable(personaService.findByIdPersona(proyect.getPersona().getId()));

        if (personaOptional.isEmpty()){
            return new ResponseEntity<>(new Mensaje("No existe la persona asociada al proyect"), HttpStatus.BAD_REQUEST);
        }

        Optional<Proyect> proyectOptional = proyectService.findByIdProyect(id);

        if (proyectOptional.isEmpty()){
            return new ResponseEntity<>(new Mensaje("Proyect que intenta modificar no encontrado"), HttpStatus.BAD_REQUEST);
        }

        proyect.setId(proyectOptional.get().getId());
        proyect.setPersona(personaOptional.get());

        proyectService.saveProyect(proyect);

        return new ResponseEntity<>(new Mensaje("Proyect actualizado"), HttpStatus.OK);
    }



}
