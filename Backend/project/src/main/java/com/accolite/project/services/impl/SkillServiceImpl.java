package com.accolite.project.services.impl;

import com.accolite.project.dao.ISkillDao;
import com.accolite.project.models.Skill;
import com.accolite.project.services.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements ISkillService {
    @Autowired
    ISkillDao iSkillDao;

    @Override
    public Skill add(Skill skill) {
        return iSkillDao.add(skill);
    }

    @Override
    public List<Skill> getAll() {
        return iSkillDao.getAll();
    }

    @Override
    public Skill getById(int id) {
        return iSkillDao.getById(id);
    }

    @Override
    public Skill getByCourseId(int courseId) {
        return iSkillDao.getByCourseId(courseId);
    }

    @Override
    public Skill updateById(int id, Skill skill) {
        return iSkillDao.updateById(id, skill);
    }

    @Override
    public boolean deleteById(int id) {
        return iSkillDao.deleteById(id);
    }
}
