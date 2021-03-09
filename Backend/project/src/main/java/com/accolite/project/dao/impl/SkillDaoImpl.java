package com.accolite.project.dao.impl;

import com.accolite.project.dao.ISkillDao;
import com.accolite.project.models.Course;
import com.accolite.project.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkillDaoImpl implements ISkillDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Skill add(Skill skill) {
        final KeyHolder holder = new GeneratedKeyHolder();

        String sql = "INSERT INTO SKILLS ( COURSE_ID, SKILL_NAME )"
                + "VALUES ( :COURSE_ID, :SKILL_NAME )";
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("COURSE_ID", skill.getCourseId());
        srcMap.addValue("SKILL_NAME", skill.getSkillName());

        namedParameterJdbcTemplate.update(sql, srcMap, holder, new String[]{"ID"});
        skill.setId(holder.getKey().intValue());
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        String sql = "SELECT * FROM SKILLS";
        return namedParameterJdbcTemplate.query(sql, (resultSet, rowNum) -> new Skill(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3)
        ));
    }

    @Override
    public Skill getById(int id) {
        String sql = "SELECT * FROM SKILLS WHERE SKILL_ID = :id";
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, srcMap, (resultSet, rowNum) -> new Skill(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3)
            ));
        }catch(Exception e) {
            return new Skill();
        }
    }

    @Override
    public Skill getByCourseId(int courseId) {
        String sql = "SELECT * FROM SKILLS WHERE COURSE_ID = :courseId";
//        System.out.println(courseId);
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("courseId", courseId);
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, srcMap, (resultSet, rowNum) -> new Skill(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3)
            ));
        }catch(Exception e) {
            return new Skill();
        }
    }

    @Override
    public Skill updateById(int id, Skill newSkill) {
        String sql = "UPDATE SKILLS SET COURSE_ID  = :COURSE_ID, SKILL_NAME = :SKILL_NAME WHERE SKILL_ID = :SKILL_ID";

        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("COURSE_ID", newSkill.getCourseId());
        srcMap.addValue("SKILL_NAME", newSkill.getSkillName());
        srcMap.addValue("SKILL_ID", id);

        namedParameterJdbcTemplate.update(sql, srcMap);

        Skill updatedSkill = this.getById(id);
        return updatedSkill;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM SKILLS WHERE SKILL_ID = :id";
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("id", id);
        return namedParameterJdbcTemplate.update(sql, srcMap) == 1? true: false;
    }
}
