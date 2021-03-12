package com.accolite.project.services.impl;

import com.accolite.project.ModelsData;
import com.accolite.project.dao.impl.SkillDaoImpl;
import com.accolite.project.models.Skill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SkillServiceImpl.class)
@RunWith(SpringRunner.class)
public class SkillServiceImplTest {
    @Autowired
    SkillServiceImpl skillService;

    @MockBean
    SkillDaoImpl skillDao;

    @Test
    public void addSkill() {
        Skill skill = ModelsData.getSkillData();

        when(skillDao.add(skill)).thenReturn(skill);

        assertThat(skillService.add(skill)).isEqualTo(skill);
    }

    @Test
    public void getAllSkill() {
        Skill skill = ModelsData.getSkillData();

        List<Skill> skills = new ArrayList<>();
        skills.add(skill);

        when(skillDao.getAll()).thenReturn(skills);

        assertThat(skillService.getAll()).isEqualTo(skills);
    }

    @Test
    public void editSkill() {
        Skill skill = ModelsData.getSkillData();

        when(skillDao.updateById(skill.getId(), skill)).thenReturn(skill);

        assertThat(skillService.updateById(skill.getId(), skill)).isEqualTo(skill);
    }

    @Test
    public void getById() {
        Skill skill = ModelsData.getSkillData();

        when(skillDao.getById(skill.getId())).thenReturn(skill);

        assertThat(skillService.getById(skill.getId())).isEqualTo(skill);
    }

    @Test
    public void getByCourseId() {
        Skill skill = ModelsData.getSkillData();

        when(skillDao.getByCourseId(ModelsData.getCourseData().getId())).thenReturn(skill);

        assertThat(skillService.getByCourseId(ModelsData.getCourseData().getId())).isEqualTo(skill);
    }

    @Test
    public void deleteById() {
        Skill skill = ModelsData.getSkillData();

        when(skillDao.deleteById(skill.getId())).thenReturn(true);

        assertThat(skillService.deleteById(skill.getId())).isTrue();
    }
}
