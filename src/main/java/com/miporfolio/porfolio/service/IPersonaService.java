package com.miporfolio.porfolio.service;

import com.miporfolio.porfolio.model.Persona;
import java.util.List;

public interface IPersonaService {
    
    public List<Persona> verPersonas();
    
    public void crearPersona(Persona pers);
    
    public void borrarPersona(Long id);
    
    public Persona buscarPersona(Long id);
    
    public void actualizarPersona(Persona pers);
}
