package com.accolite.project.services.impl;

import com.accolite.project.dao.ICourseRDDao;
import com.accolite.project.models.Course;
import com.accolite.project.services.ICourseRDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseRDServiceImpl implements ICourseRDService {
    @Autowired
    ICourseRDDao iCourseRDDao;

    @Override
    public Course getById(int id) {
        return iCourseRDDao.getById(id);
    }

    @Override
    public List<Course> getAll() {
        return iCourseRDDao.getAll();
    }

    @Override
    public boolean deleteById(int id) {
        return iCourseRDDao.deleteById(id);
    }
}
