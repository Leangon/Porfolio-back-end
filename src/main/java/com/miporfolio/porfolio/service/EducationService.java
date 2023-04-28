package com.miporfolio.porfolio.service;

import com.miporfolio.porfolio.model.Education;
import com.miporfolio.porfolio.repository.EducationRepository;
import com.miporfolio.porfolio.service.impl.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EducationService implements IEducationService {

    @Autowired
    EducationRepository educationRepository;

    @Override
    public List<Education> listEducation() {
        return educationRepository.findAll();
    }

    @Override
    public void saveEducation(Education education) {

        educationRepository.save(education);
    }

    @Override
    public void deleteEducation(int id) {
        educationRepository.deleteById(id);
    }

    @Override
    public Optional<Education> findExperience(int id) {
        return educationRepository.findById(id);
    }
}
