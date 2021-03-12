package com.accolite.project.services.impl;


import com.accolite.project.ModelsData;
import com.accolite.project.dao.impl.CourseCUDaoImpl;
import com.accolite.project.models.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CourseCUServiceImpl.class)
@RunWith(SpringRunner.class)
public class CourseCUServiceImplTest {
    @Autowired
    CourseCUServiceImpl courseService;

    @MockBean
    CourseCUDaoImpl courseDao;

    @Test
    public void createCourse() {
        Course course = ModelsData.getCourseData();

        when(courseDao.add(course)).thenReturn(course);

        assertThat(courseService.add(course)).isEqualTo(course);
    }

    @Test
    public void updateById() {
        Course course = ModelsData.getCourseData();

        when(courseDao.updateById(course.getId(), course)).thenReturn(course);

        assertThat(courseService.updateById(course.getId(), course)).isEqualTo(course);
    }
}
