package com.accolite.project.services.impl;

import com.accolite.project.dao.IFeedbackDao;
import com.accolite.project.models.Feedback;
import com.accolite.project.services.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements IFeedbackService {
    @Autowired
    IFeedbackDao iFeedbackDao;

    @Override
    public Feedback add(Feedback feedback) {
        return iFeedbackDao.add(feedback);
    }

    @Override
    public List<Feedback> getAll() {
        return iFeedbackDao.getAll();
    }

    @Override
    public List<Feedback> getByCourseId(int id) {
        return iFeedbackDao.getByCourseId(id);
    }

    @Override
    public String getAvgRatingOnCourse(int courseId) {
        return iFeedbackDao.getAvgRatingOnCourse(courseId);
    }
}
