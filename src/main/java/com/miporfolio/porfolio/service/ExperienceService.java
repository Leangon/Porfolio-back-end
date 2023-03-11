package com.miporfolio.porfolio.service;

import com.miporfolio.porfolio.model.Experience;
import com.miporfolio.porfolio.repository.ExperienceRepository;
import com.miporfolio.porfolio.service.impl.IExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExperienceService implements IExperienceService {

    @Autowired
    ExperienceRepository experienceRepository;

    @Override
    public List<Experience> listExperience() {
        return experienceRepository.findAll();
    }

    @Override
    public void saveExperience(Experience experience) {
        experienceRepository.save(experience);
    }

    @Override
    public void deleteExperience(int id) {
        experienceRepository.deleteById(id);
    }

    @Override
    public Optional<Experience> findExperience(int id) {
        return experienceRepository.findById(id);
    }
}
