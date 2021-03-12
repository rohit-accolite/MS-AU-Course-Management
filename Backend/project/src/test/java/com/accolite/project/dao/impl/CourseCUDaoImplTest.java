package com.accolite.project.dao.impl;

import com.accolite.project.ModelsData;
import com.accolite.project.ProjectApplication;
import com.accolite.project.models.Course;
import com.accolite.project.models.Skill;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProjectApplication.class)
public class CourseCUDaoImplTest {
    @Autowired
    CourseCUDaoImpl courseCUDao;

    @Autowired
    CourseRDDaoImpl courseRDDao;

    @MockBean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void courseUpdate() {

        Course course = ModelsData.getCourseData();

        when(courseRDDao.getById(course.getId())).thenReturn(course);
        when(namedParameterJdbcTemplate.queryForObject(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class), Mockito.any(RowMapper.class))).thenReturn(course);
        when(namedParameterJdbcTemplate.update(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class))).thenReturn(1);

        Course updated = courseCUDao.updateById(course.getId(), course);
        assertEquals(course, updated);
    }
}
