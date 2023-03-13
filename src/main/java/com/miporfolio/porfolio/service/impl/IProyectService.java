package com.miporfolio.porfolio.service.impl;

import com.miporfolio.porfolio.model.Proyect;

import java.util.List;
import java.util.Optional;

public interface IProyectService {

    List<Proyect> listProyects();

    void saveProyect(Proyect proyect);

    void deleteProyect(int id);

    Optional<Proyect> findByIdProyect(int id);

}
