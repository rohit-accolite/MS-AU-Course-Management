package com.accolite.project.dao.impl;

import com.accolite.project.dao.IFeedbackDao;
import com.accolite.project.models.Feedback;
import com.accolite.project.models.Skill;
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
        return feedback;
    }

    @Override
    public List<Feedback> getAll() {
        String sql = "SELECT * FROM FEEDBACKS";
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

        return namedParameterJdbcTemplate.query(sql, srcMap, (resultSet, rowNum) -> new Feedback(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getInt(5),
                resultSet.getString(6)
        ));
    }
}
