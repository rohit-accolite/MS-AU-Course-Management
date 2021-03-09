package com.accolite.project.dao;

import com.accolite.project.models.Course;

import java.util.List;

public interface ICourseRDDao {
    Course getById(int id);

    List<Course> getAll();

    boolean deleteById(int id);
}
