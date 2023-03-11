package com.miporfolio.porfolio.service.impl;

import com.miporfolio.porfolio.model.Education;

import java.util.List;
import java.util.Optional;

public interface IEducationService {

    List<Education> listEducation();

    void saveEducation(Education education);

    void deleteEducation(int id);

    Optional<Education> findExperience(int id);
}
