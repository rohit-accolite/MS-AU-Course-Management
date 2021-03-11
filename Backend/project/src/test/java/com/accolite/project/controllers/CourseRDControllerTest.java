package com.accolite.project.controllers;

import com.accolite.project.ModelsData;
import com.accolite.project.models.Course;
import com.accolite.project.services.ICourseRDService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CourseRDController.class)
public class CourseRDControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ICourseRDService courseService;

    @Test
    void getAllCourses() throws Exception {
        Course course = ModelsData.getCourseData();

        List<Course> courses = new ArrayList<>();
        courses.add(course);

        when(courseService.getAll()).thenReturn(courses);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/course-rd/get-all"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result).isNotNull();
    }

    @Test
    void getById() throws Exception {
        Course course = ModelsData.getCourseData();

        when(courseService.getById(course.getId())).thenReturn(course);

        mockMvc.perform(MockMvcRequestBuilders.get("/course-rd/get-by-id/" + course.getId()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteById() throws Exception {
        Course course = ModelsData.getCourseData();

        when(courseService.deleteById(course.getId())).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/course-rd/delete/" + course.getId()))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("true");
    }
}
