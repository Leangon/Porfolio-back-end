package com.miporfolio.porfolio.service.impl;

import com.miporfolio.porfolio.model.Experience;

import java.util.List;
import java.util.Optional;

public interface IExperienceService {

    List<Experience> listExperience();

    void saveExperience(Experience experience);

    void deleteExperience(int id);

    Optional<Experience> findExperience(int id);
}
