package com.accolite.project.dao.impl;

import com.accolite.project.dao.IFeedbackDao;
import com.accolite.project.models.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackDaoImpl implements IFeedbackDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    Logger logger = LoggerFactory.getLogger(FeedbackDaoImpl.class);

    @Override
    public Feedback add(Feedback feedback) {
        final KeyHolder holder = new GeneratedKeyHolder();

        String sql = "INSERT INTO FEEDBACKS ( COURSE_ID, PARTICIPANT_NAME, FEEDBACK_TEXT, RATING, CREATED_ON )"
                + "VALUES ( :COURSE_ID, :PARTICIPANT_NAME, :FEEDBACK_TEXT, :RATING, :CREATED_ON )";
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("COURSE_ID", feedback.getCourseId());
        srcMap.addValue("PARTICIPANT_NAME", feedback.getParticipantName());
        srcMap.addValue("FEEDBACK_TEXT", feedback.getFeedbackText());
        srcMap.addValue("RATING", feedback.getRating());
        srcMap.addValue("CREATED_ON", feedback.getCreatedOn());

        namedParameterJdbcTemplate.update(sql, srcMap, holder, new String[]{"ID"});
        feedback.setId(holder.getKey().intValue());
        logger.info("Added feedback with Id {} for course with Id {}", holder.getKey(), feedback.getCourseId());
        return feedback;
    }

    @Override
    public List<Feedback> getAll() {
        String sql = "SELECT * FROM FEEDBACKS";
        logger.info("Retrieved all the feedbacks");
        return namedParameterJdbcTemplate.query(sql, (resultSet, rowNum) -> new Feedback(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getInt(5),
                resultSet.getString(6)
        ));
    }

    @Override
    public List<Feedback> getByCourseId(int courseId) {
        String sql = "SELECT * FROM FEEDBACKS WHERE COURSE_ID = :COURSE_ID";
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("COURSE_ID", courseId);
        logger.info("Retrieving all the feedbacks for course with Id {}", courseId);
        return namedParameterJdbcTemplate.query(sql, srcMap, (resultSet, rowNum) -> new Feedback(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getInt(5),
                resultSet.getString(6)
        ));
    }

    @Override
    public String getAvgRatingOnCourse(int courseId) {
        String sql = "SELECT AVG(RATING) FROM FEEDBACKS WHERE COURSE_ID = :COURSE_ID";
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("COURSE_ID", courseId);
        try {
            Float result = (namedParameterJdbcTemplate.queryForObject(sql, srcMap, Float.class));
            logger.info("Retrieved average rating on course with Id {}", courseId);
            return result.toString();
        } catch (Exception e) {
            logger.info("Course with Id {} has no ratings",  courseId);
            return "0";
        }

    }
}
