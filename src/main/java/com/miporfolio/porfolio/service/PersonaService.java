package com.miporfolio.porfolio.service;

import com.miporfolio.porfolio.model.Persona;
import com.miporfolio.porfolio.repository.PersonaRepository;
import java.util.List;
import java.util.Optional;

import com.miporfolio.porfolio.service.impl.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonaService implements IPersonaService {

    @Autowired
    public PersonaRepository personaRepository;
    
    @Override
    public List<Persona> verPersonas() {

        return personaRepository.findAll();
    }

    @Override
    public void savePersona(Persona pers) {

        personaRepository.save(pers);
    }

    @Override
    public void deletePersona(int id) {

        personaRepository.deleteById(id);
    }

    @Override
    public Persona findByIdPersona(int id) {

        return personaRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsByIdPersona(int id) {
        return personaRepository.existsById(id);
    }

    @Override
    public Optional<Persona> findByNamePersona(String name) {
        return personaRepository.findByName(name);
    }

    @Override
    public boolean existsNamePersona(String name) {
        return personaRepository.existsByName(name);
    }

}