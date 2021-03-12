package com.accolite.project.dao.impl;

import com.accolite.project.ModelsData;
import com.accolite.project.ProjectApplication;
import com.accolite.project.models.Course;
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
public class CourseRDDaoImplTest {
    @Autowired
    CourseRDDaoImpl courseDao;

    @MockBean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void getAll() {
        Course course = ModelsData.getCourseData();

        List<Course> courses = new ArrayList<>();
        courses.add(course);

        when(namedParameterJdbcTemplate.query(Mockito.any(String.class), Mockito.any(RowMapper.class))).thenReturn(courses);

        List<Course> result = courseDao.getAll();
        assertEquals(courses, result);
    }

    @Test
    public void getById() {
        Course course = ModelsData.getCourseData();

        when(namedParameterJdbcTemplate.queryForObject(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class), Mockito.any(RowMapper.class))).thenReturn(course);

        Course result =courseDao.getById(course.getId());
        assertEquals(course, result);
    }

    @Test
    public void deleteCourse() {
        Course course = ModelsData.getCourseData();

        when(namedParameterJdbcTemplate.update(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class))).thenReturn(1);

        boolean isDeleted = courseDao.deleteById(course.getId());
        assertEquals(true, isDeleted);
    }
}
