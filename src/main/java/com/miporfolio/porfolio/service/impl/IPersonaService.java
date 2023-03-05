package com.miporfolio.porfolio.service.impl;

import com.miporfolio.porfolio.model.Persona;
import java.util.List;
import java.util.Optional;

public interface IPersonaService {
    
    List<Persona> verPersonas();
    
    void savePersona(Persona pers);
    
    void deletePersona(int id);
    
    Persona findByIdPersona(int id);

    boolean existsByIdPersona(int id);

    Optional<Persona> findByNamePersona(String name);

    boolean existsNamePersona(String name);

}
