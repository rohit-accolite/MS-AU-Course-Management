package com.accolite.project.controllers;

import com.accolite.project.ModelsData;
import com.accolite.project.models.Course;
import com.accolite.project.services.ICourseCUService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CourseCUController.class)
public class CourseCUControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ICourseCUService courseService;

    @Test
    void createCourse() throws Exception {
        Course course = ModelsData.getCourseData();

        when(courseService.add(course)).thenReturn(course);

        mockMvc.perform(MockMvcRequestBuilders.post("/course-cu/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(course))
        ).andExpect(status().isOk()).andReturn();
    }

    @Test
    void editCourse() throws Exception {
        Course course = ModelsData.getCourseData();

        when(courseService.updateById(course.getId(), course)).thenReturn(course);

        mockMvc.perform(MockMvcRequestBuilders.put("/course-cu/update/" + course.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(course))
        ).andExpect(status().isOk()).andReturn();
    }
}
