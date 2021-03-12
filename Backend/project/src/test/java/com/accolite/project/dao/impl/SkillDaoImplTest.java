package com.accolite.project.dao.impl;

import com.accolite.project.ModelsData;
import com.accolite.project.ProjectApplication;
import com.accolite.project.models.Skill;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProjectApplication.class)
public class SkillDaoImplTest {
    @Autowired
    SkillDaoImpl skillDao;

    @MockBean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void skillUpdate() {

        Skill skill = ModelsData.getSkillData();

        when(skillDao.getById(skill.getId())).thenReturn(skill);
        when(namedParameterJdbcTemplate.queryForObject(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class), Mockito.any(RowMapper.class))).thenReturn(skill);
        when(namedParameterJdbcTemplate.update(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class))).thenReturn(1);

        Skill updated = skillDao.updateById(skill.getId(), skill);
        assertEquals(skill, updated);
    }

    @Test
    public void getAllSkills() {
        Skill skill = ModelsData.getSkillData();

        List<Skill> skills = new ArrayList<>();
        skills.add(skill);

        when(namedParameterJdbcTemplate.query(Mockito.any(String.class), Mockito.any(RowMapper.class))).thenReturn(skills);

        List<Skill> allSkills = skillDao.getAll();
        assertEquals(skills, allSkills);
    }

    @Test
    public void getSkillByCourseId() {
        Skill skill = ModelsData.getSkillData();

        when(namedParameterJdbcTemplate.queryForObject(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class), Mockito.any(RowMapper.class))).thenReturn(skill);

        Skill result = skillDao.getByCourseId(skill.getCourseId());
        assertEquals(skill, result);
    }

    @Test
    public void getSkillById() {
        Skill skill = ModelsData.getSkillData();

        when(namedParameterJdbcTemplate.queryForObject(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class), Mockito.any(RowMapper.class))).thenReturn(skill);

        Skill result = skillDao.getById(skill.getId());
        assertEquals(skill, result);
    }

    @Test
    public void deleteSkill() {
        Skill skill = ModelsData.getSkillData();

        when(namedParameterJdbcTemplate.update(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class))).thenReturn(1);

        boolean isDeleted = skillDao.deleteById(skill.getId());
        assertEquals(true, isDeleted);
    }
/*
    @Test
    public void addSkill() {
        Skill skill = ModelsData.getSkillData();
//        KeyHolder holder = new GeneratedKeyHolder();
//        when(KeyHolder.class).getKey().intValue().thenReturn(1);
//        KeyHolder holder = Mockito.mock(KeyHolder.class);
        KeyHolder holder = mock(KeyHolder.class);
        when(holder.getKey().intValue()).thenReturn(1);

        when(namedParameterJdbcTemplate.update(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class),
                Mockito.any(KeyHolder.class), Mockito.any(String[].class))).thenReturn(1);
//        Mockito.doReturn(1).when(holder).getKey().intValue();


        Skill rowsAdded=skillDao.add(skill);
        assertEquals(1, rowsAdded);
    }

 */
}
