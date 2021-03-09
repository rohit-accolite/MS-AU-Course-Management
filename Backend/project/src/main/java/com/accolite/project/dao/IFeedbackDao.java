package com.accolite.project.dao;

import com.accolite.project.models.Feedback;

import java.util.List;

public interface IFeedbackDao {
    Feedback add(Feedback feedback);

    List<Feedback> getAll();

    List<Feedback> getByCourseId(int courseId);
}
