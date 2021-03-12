package com.accolite.project.services.impl;

import com.accolite.project.ModelsData;
import com.accolite.project.dao.impl.CourseRDDaoImpl;
import com.accolite.project.models.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CourseRDServiceImpl.class)
@RunWith(SpringRunner.class)
public class CourseRDServiceImplTest {

    @Autowired
    CourseRDServiceImpl courseService;

    @MockBean
    CourseRDDaoImpl courseDao;

    @Test
    public void getAllCourses() {
        Course course = ModelsData.getCourseData();

        List<Course> courses = new ArrayList<>();
        courses.add(course);

        when(courseDao.getAll()).thenReturn(courses);

        assertThat(courseService.getAll()).isEqualTo(courses);
    }

    @Test
    public void getById() {
        Course course = ModelsData.getCourseData();

        when(courseDao.getById(course.getId())).thenReturn(course);

        assertThat(courseService.getById(course.getId())).isEqualTo(course);
    }

    @Test
    public void deleteById() {
        Course course = ModelsData.getCourseData();

        when(courseDao.deleteById(course.getId())).thenReturn(true);

        assertThat(courseService.deleteById(course.getId())).isTrue();

    }
}
