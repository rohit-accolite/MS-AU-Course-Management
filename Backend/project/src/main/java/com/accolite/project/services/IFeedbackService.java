package com.accolite.project.services;

import com.accolite.project.models.Feedback;

import java.util.List;

public interface IFeedbackService {
    Feedback add(Feedback feedback);

    List<Feedback> getAll();

    List<Feedback> getByCourseId(int courseId);

    String getAvgRatingOnCourse(int courseId);
}
