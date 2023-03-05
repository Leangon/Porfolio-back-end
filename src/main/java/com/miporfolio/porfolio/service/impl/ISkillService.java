package com.miporfolio.porfolio.service.impl;

import com.miporfolio.porfolio.model.Skill;

import java.util.List;

public interface ISkillService {

    List<Skill> showSkills();

    void saveSkill(Skill skill);

    void deleteSkill(Long id);

    Skill findSkill(Long id);

}
