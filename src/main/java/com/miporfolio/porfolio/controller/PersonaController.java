package com.miporfolio.porfolio.controller;

import com.miporfolio.porfolio.model.Persona;
import com.miporfolio.porfolio.service.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "*")
public class PersonaController {
    
    @Autowired
    private IPersonaService persoServ;
    
    @PostMapping ("/new")
    public void agregarPersona (@RequestBody Persona pers){
        persoServ.crearPersona(pers);
    }

    @PreAuthorize("hasrole('ADMIN')")
    @GetMapping ("/verlistapersonas")
    @ResponseBody
    public List<Persona> verPersonas(){
        return persoServ.verPersonas();
    }

    @PreAuthorize("hasrole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void borrarPersona(@PathVariable Long id){
        persoServ.borrarPersona(id);
    }
    
    @GetMapping ("/buscar/{id}")
    @ResponseBody
    public Persona buscarPersona(@PathVariable Long id){
        return persoServ.buscarPersona(id);
    }

    @PreAuthorize("hasrole('ADMIN')")
    @PutMapping ("/actualizar")
    public void actualizarPersona(@RequestBody Persona pers){
        persoServ.actualizarPersona(pers);
    }
    
}
