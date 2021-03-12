package com.accolite.project.services.impl;

import com.accolite.project.ModelsData;
import com.accolite.project.dao.impl.FeedbackDaoImpl;
import com.accolite.project.models.Feedback;
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

@SpringBootTest(classes = FeedbackServiceImpl.class)
@RunWith(SpringRunner.class)
public class FeedbackServiceImplTest {
    @Autowired
    FeedbackServiceImpl feedbackService;

    @MockBean
    FeedbackDaoImpl feedbackDao;

    @Test
    public void addFeedback() {
        Feedback feedback = ModelsData.getFeedbackData();

        when(feedbackDao.add(feedback)).thenReturn(feedback);

        assertThat(feedbackService.add(feedback)).isEqualTo(feedback);
    }

    @Test
    public void getAllFeedbacks() {
        Feedback feedback = ModelsData.getFeedbackData();

        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback);

        when(feedbackDao.getAll()).thenReturn(feedbacks);

        assertThat(feedbackService.getAll()).isEqualTo(feedbacks);
    }

    @Test
    public void getByCourseId() {
        Feedback feedback = ModelsData.getFeedbackData();

        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback);

        when(feedbackDao.getByCourseId(ModelsData.getCourseData().getId())).thenReturn(feedbacks);

        assertThat(feedbackService.getByCourseId(ModelsData.getCourseData().getId())).isEqualTo(feedbacks);
    }

    @Test
    public void getAvgRatingOnCourse() {
        when(feedbackDao.getAvgRatingOnCourse(ModelsData.getCourseData().getId())).thenReturn("4");

        assertThat(feedbackService.getAvgRatingOnCourse(ModelsData.getCourseData().getId())).isEqualTo("4");
    }
}
