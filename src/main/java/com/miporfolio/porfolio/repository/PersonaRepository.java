package com.miporfolio.porfolio.repository;

import com.miporfolio.porfolio.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>{

    Optional<Persona> findByName(String name);
    boolean existsByName(String name);
}
