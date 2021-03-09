package com.accolite.project.services.impl;

import com.accolite.project.dao.ICourseCUDao;
import com.accolite.project.models.Course;
import com.accolite.project.services.ICourseCUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseCUServiceImpl implements ICourseCUService {
    @Autowired
    ICourseCUDao iCourseCUDao;

    @Override
    public Course add(Course course) {
        return iCourseCUDao.add(course);
    }

    @Override
    public Course updateById(int id, Course course) {
        return iCourseCUDao.updateById(id, course);
    }
}
