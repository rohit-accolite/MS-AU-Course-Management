package com.accolite.project.services;

import com.accolite.project.models.Course;

public interface ICourseCUService {
    Course add(Course course);

    Course updateById(int id, Course course);

}
