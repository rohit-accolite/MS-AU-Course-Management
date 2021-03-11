package com.accolite.project.controllers;

import com.accolite.project.ModelsData;
import com.accolite.project.models.Course;
import com.accolite.project.models.Feedback;
import com.accolite.project.services.IFeedbackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FeedbackController.class)
public class FeedbackControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    IFeedbackService feedbackService;

    @Test
    void getAllFeedbacks() throws Exception {
        Feedback f1 = ModelsData.getFeedbackData();

        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(f1);

        when(feedbackService.getAll()).thenReturn(feedbacks);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/feedback/get-all"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result).isNotNull();
    }

    @Test
    void createCourse() throws Exception {
        Feedback feedback = ModelsData.getFeedbackData();

        when(feedbackService.add(feedback)).thenReturn(feedback);
        mockMvc.perform(MockMvcRequestBuilders.post("/feedback/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(feedback))
        ).andExpect(status().isOk()).andReturn();
    }

    @Test
    void getFeedbacksByCourseId() throws Exception {
        Feedback feedback = ModelsData.getFeedbackData();

        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback);

        when(feedbackService.getByCourseId(ModelsData.getCourseData().getId())).thenReturn(feedbacks);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/feedback/get-by-course-id/" + ModelsData.getCourseData().getId()))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result).isNotNull();
    }

    @Test
    void getAvgRatingOnCourse() throws Exception {
        when(feedbackService.getAvgRatingOnCourse(ModelsData.getCourseData().getId())).thenReturn("4");
        mockMvc.perform(MockMvcRequestBuilders.get("/feedback/avg-rating-on-course/" + ModelsData.getCourseData().getId()))
                .andExpect(status().isOk())
                .andReturn();
    }

}
