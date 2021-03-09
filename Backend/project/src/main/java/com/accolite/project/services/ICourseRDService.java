package com.accolite.project.services;

import com.accolite.project.models.Course;

import java.util.List;

public interface ICourseRDService {
    Course getById(int id);

    List<Course> getAll();

    boolean deleteById(int id);
}
