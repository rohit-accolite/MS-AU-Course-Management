package com.accolite.project.dao.impl;

import com.accolite.project.ModelsData;
import com.accolite.project.ProjectApplication;
import com.accolite.project.models.Material;
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
public class MaterialDaoImplTest {
    @Autowired
    MaterialDaoImpl materialDao;

    @MockBean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void getMaterialById() {
        Material material = ModelsData.getMaterialData();

        when(namedParameterJdbcTemplate.queryForObject(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class), Mockito.any(RowMapper.class))).thenReturn(material);

        Material result = materialDao.getById(material.getId());
        assertEquals(material, result);
    }

    @Test
    public void getMaterialsByCourseId() {
        Material material = ModelsData.getMaterialData();

        List<Material> materials = new ArrayList<>();
        materials.add(material);

        when(namedParameterJdbcTemplate.query(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class), Mockito.any(RowMapper.class))).thenReturn(materials);

        List<Material> result = materialDao.getMaterialsByCourseId(material.getCourseId());
        assertEquals(materials, result);
    }



    @Test
    public void deleteMaterial() {
        Material material = ModelsData.getMaterialData();

        when(namedParameterJdbcTemplate.update(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class))).thenReturn(1);

        boolean isDeleted = materialDao.deleteMaterialById(material.getId());
        assertEquals(true, isDeleted);
    }
}
