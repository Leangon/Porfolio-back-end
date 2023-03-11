package com.miporfolio.porfolio.service;

import com.miporfolio.porfolio.model.Skill;
import com.miporfolio.porfolio.repository.SkillRepository;
import com.miporfolio.porfolio.service.impl.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SkillService implements ISkillService {

    @Autowired
    SkillRepository skillRepository;

    @Override
    public List<Skill> showSkills() {

        return skillRepository.findAll();
    }

    @Override
    public void saveSkill(Skill skill) {

        skillRepository.save(skill);
    }

    @Override
    public void deleteSkill(Long id) {

        skillRepository.deleteById(id);
    }

    @Override
    public Skill findSkill(Long id) {

        return skillRepository.findById(id).orElse(null);
    }
}
