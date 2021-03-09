package com.accolite.project.dao;

import com.accolite.project.models.Course;

public interface ICourseCUDao {
    Course add (Course course);

    Course updateById(int id, Course course);
}
