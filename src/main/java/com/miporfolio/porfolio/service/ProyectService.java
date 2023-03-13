package com.miporfolio.porfolio.service;

import com.miporfolio.porfolio.model.Proyect;
import com.miporfolio.porfolio.repository.ProyectRepository;
import com.miporfolio.porfolio.service.impl.IProyectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProyectService implements IProyectService {

    @Autowired
    ProyectRepository proyectRepository;

    @Override
    public List<Proyect> listProyects() {
        return proyectRepository.findAll();
    }

    @Override
    public void saveProyect(Proyect proyect) {
        proyectRepository.save(proyect);
    }

    @Override
    public void deleteProyect(int id) {
        proyectRepository.deleteById(id);
    }

    @Override
    public Optional<Proyect> findByIdProyect(int id) {
        return proyectRepository.findById(id);
    }
}
