package com.accolite.project.services;

import com.accolite.project.models.Skill;

import java.util.List;

public interface ISkillService {
    Skill add(Skill skill);

    List<Skill> getAll();

    Skill getById(int id);

    Skill getByCourseId(int courseId);

    Skill updateById(int id, Skill skill);

    boolean deleteById(int id);
}
