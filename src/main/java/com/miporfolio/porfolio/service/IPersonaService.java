package com.miporfolio.porfolio.service;

import com.miporfolio.porfolio.model.Persona;
import java.util.List;

public interface IPersonaService {
    
    List<Persona> verPersonas();
    
    void crearPersona(Persona pers);
    
    void borrarPersona(Long id);
    
    Persona buscarPersona(Long id);
    
    void actualizarPersona(Persona pers);
}
