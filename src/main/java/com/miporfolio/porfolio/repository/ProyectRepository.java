package com.miporfolio.porfolio.repository;

import com.miporfolio.porfolio.model.Proyect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectRepository extends JpaRepository<Proyect, Integer> {

}
