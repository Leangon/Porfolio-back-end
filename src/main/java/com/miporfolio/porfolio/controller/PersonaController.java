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
    public void savePersona (@RequestBody Persona pers){

        personaService.savePersona(pers);
    }

    @GetMapping ("/personaVerLista")
    @ResponseBody
    public ResponseEntity<List<Persona>> verPersonas(){
        List<Persona> listPersona = personaService.verPersonas();
        return new ResponseEntity<>(listPersona, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/personaDelete/{id}")
    public void borrarPersona(@PathVariable int id){

        personaService.deletePersona(id);
    }
    
    @GetMapping ("/personaBuscar/{id}")
    @ResponseBody
    public Persona buscarPersona(@PathVariable int id){

        return personaService.findByIdPersona(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping ("/personaActualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody Persona persona){
        if (!personaService.existsByIdPersona(id)){
            return new ResponseEntity<>(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(persona.getName())){
            return new ResponseEntity<>(new Mensaje("El nombre no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Optional<Persona> personaOptional = Optional.ofNullable(personaService.findByIdPersona(id));

        if (personaOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        persona.setId(personaOptional.get().getId());
        personaService.savePersona(persona);

        return new ResponseEntity<>(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }
    
}
