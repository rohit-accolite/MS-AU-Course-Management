package com.accolite.project.dao.impl;

import com.accolite.project.models.Course;
import com.accolite.project.dao.ICourseRDDao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

@Repository
public class CourseRDDaoImpl implements ICourseRDDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Course getById(int id) {
        String sql = "SELECT * FROM COURSES WHERE COURSE_ID = :id";
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, srcMap, (resultSet, rowNum) -> new Course(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getInt(7)
            ));
        } catch (Exception e) {
            return new Course();
        }
    }

    @Override
    public List<Course> getAll() {
        String sql = "SELECT * FROM COURSES";
        return namedParameterJdbcTemplate.query(sql, (resultSet, rowNum) -> new Course(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getInt(7)
        ));
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM COURSES WHERE COURSE_ID = :id";
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("id", id);
        return namedParameterJdbcTemplate.update(sql, srcMap) == 1 ? true : false;
    }
}
