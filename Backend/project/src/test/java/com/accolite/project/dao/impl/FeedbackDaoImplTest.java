package com.accolite.project.dao.impl;

import com.accolite.project.ModelsData;
import com.accolite.project.ProjectApplication;
import com.accolite.project.models.Feedback;
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
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProjectApplication.class)
public class FeedbackDaoImplTest {

    @Autowired
    FeedbackDaoImpl feedbackDao;

    @MockBean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void getAllFeedbacks() {
        Feedback feedback = ModelsData.getFeedbackData();

        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback);

        when(namedParameterJdbcTemplate.query(Mockito.any(String.class), Mockito.any(RowMapper.class))).thenReturn(feedbacks);

        List<Feedback> result = feedbackDao.getAll();
        assertEquals(feedbacks, result);
    }

    @Test
    public void getFeedbacksByCourseId() {
        Feedback feedback = ModelsData.getFeedbackData();

        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback);

        when(namedParameterJdbcTemplate.query(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class), Mockito.any(RowMapper.class))).thenReturn(feedbacks);

        List<Feedback> result = feedbackDao.getByCourseId(feedback.getCourseId());
        assertEquals(feedbacks, result);
    }

    @Test
    public void getAvgRatingOnCourse() {
        Feedback feedback = ModelsData.getFeedbackData();

        when(namedParameterJdbcTemplate.queryForObject(Mockito.any(String.class), Mockito.any(MapSqlParameterSource.class), refEq(Float.class))).thenReturn(4.0f);

        String result = feedbackDao.getAvgRatingOnCourse(feedback.getCourseId());
        assertEquals("4.0", result);
    }
}
