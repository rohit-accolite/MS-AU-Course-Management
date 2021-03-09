package com.accolite.project.dao.impl;

import com.accolite.project.dao.ICourseCUDao;
import com.accolite.project.dao.ICourseRDDao;
import com.accolite.project.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CourseCUDaoImpl implements ICourseCUDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    ICourseRDDao iCourseRDDao;

    @Override
    public Course add(Course course) {
        final KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO COURSES ( COURSE_NAME, COURSE_DESCRIPTION, PREREQUISITE, CREATED_ON, LAST_MODIFIED_ON, USER_ID )"
                + "VALUES ( :COURSE_NAME, :COURSE_DESCRIPTION, :PREREQUISITE, :CREATED_ON, :LAST_MODIFIED_ON, :USER_ID )";
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("COURSE_NAME", course.getCourseName());
        srcMap.addValue("COURSE_DESCRIPTION", course.getCourseDescription());
        srcMap.addValue("PREREQUISITE", course.getPreRequisite());
        srcMap.addValue("CREATED_ON", course.getCreatedOn());
        srcMap.addValue("LAST_MODIFIED_ON", course.getLastModifiedOn());
        srcMap.addValue("USER_ID", course.getCreatedBy());

        namedParameterJdbcTemplate.update(sql, srcMap, holder, new String[]{"ID"});
        course.setId(holder.getKey().intValue());
        return course;
    }

    @Override
    public Course updateById(int id, Course newCourse) {
//        Course oldCourse = iCourseRDDao.getById(id);

//        if (!areSame(oldCourse, newCourse)) {
            String sql = "UPDATE COURSES SET COURSE_NAME  = :COURSE_NAME, COURSE_DESCRIPTION = :COURSE_DESCRIPTION," +
                    " PREREQUISITE = :PREREQUISITE, LAST_MODIFIED_ON = :LAST_MODIFIED_ON WHERE COURSE_ID = :COURSE_ID";
            MapSqlParameterSource srcMap = new MapSqlParameterSource();
            srcMap.addValue("COURSE_NAME", newCourse.getCourseName());
            srcMap.addValue("COURSE_DESCRIPTION", newCourse.getCourseDescription());
            srcMap.addValue("PREREQUISITE", newCourse.getPreRequisite());
            srcMap.addValue("LAST_MODIFIED_ON", newCourse.getLastModifiedOn());
            srcMap.addValue("COURSE_ID", id);

            namedParameterJdbcTemplate.update(sql, srcMap);

            Course updatedCourse = iCourseRDDao.getById(id);
            return updatedCourse;
//        }
//        return new Course();
    }

    public boolean areSame(Course oldCourse, Course newCourse) {
        if (oldCourse.getCourseName().equals(newCourse.getCourseName()) && oldCourse.getCourseDescription().equals(newCourse.getCourseDescription())
                && oldCourse.getPreRequisite().equals(newCourse.getPreRequisite())) {
            return true;
        } else return false;
    }
}
