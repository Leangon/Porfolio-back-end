package com.miporfolio.porfolio.controller;

import com.miporfolio.porfolio.dto.Mensaje;
import com.miporfolio.porfolio.dto.PersonaDto;
import com.miporfolio.porfolio.model.Persona;
import com.miporfolio.porfolio.service.impl.IPersonaService;
import java.util.List;
import java.util.Optional;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonaController {
    
    @Autowired
    private IPersonaService personaService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/personaNew")
    public ResponseEntity<?> savePersona (@RequestBody Persona pers){

        personaService.savePersona(pers);
        return new ResponseEntity(new Mensaje("Persona creada"), HttpStatus.OK);
    }

    @GetMapping ("/personaVerLista")
    @ResponseBody
    public ResponseEntity<List<Persona>> verPersonas(){
        List<Persona> listPersona = personaService.verPersonas();
        return new ResponseEntity<List<Persona>>(listPersona, HttpStatus.OK);
    }

    /*@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/personaDelete/{id}")
    public void borrarPersona(@PathVariable int id){

        personaService.deletePersona(id);
    }*/
    
    @GetMapping ("/personaFind/{id}")
    @ResponseBody
    public ResponseEntity<Persona> buscarPersona(@PathVariable int id){
        if (!personaService.existsByIdPersona(id))
            return new ResponseEntity(new Mensaje("No existe la person"), HttpStatus.NOT_FOUND);

        Persona persona = personaService.findByIdPersona(id);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping ("/personUpdate/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable int id, @RequestBody Persona persona){
        if (!personaService.existsByIdPersona(id)){
            return new ResponseEntity<>(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(persona.getTitle())){
            return new ResponseEntity<>(new Mensaje("El titulo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Optional<Persona> personaOptional = Optional.ofNullable(personaService.findByIdPersona(id));

        if (personaOptional.isEmpty()){
            return new ResponseEntity<>(new Mensaje("No existe la persona"), HttpStatus.BAD_REQUEST);
        }

        persona.setId(personaOptional.get().getId());
        persona.setEmail(personaOptional.get().getEmail());
        persona.setName(personaOptional.get().getName());
        persona.setPhoneNumber(personaOptional.get().getPhoneNumber());
        personaService.savePersona(persona);

        return new ResponseEntity<>(new Mensaje("Person actualize!"), HttpStatus.OK);
    }
    
}
