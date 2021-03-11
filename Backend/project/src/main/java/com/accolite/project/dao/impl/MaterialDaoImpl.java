package com.accolite.project.dao.impl;

import com.accolite.project.dao.IMaterialDao;
import com.accolite.project.models.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Repository
public class MaterialDaoImpl implements IMaterialDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    Logger logger = LoggerFactory.getLogger(MaterialDaoImpl.class);

    @Override
    public Material add(Material material, MultipartFile file) {
        final KeyHolder holder = new GeneratedKeyHolder();

        String sql = "INSERT INTO MATERIALS ( COURSE_ID, FILE_NAME, FILE_TYPE, FILE_DATA, CREATED_ON )"
                + "VALUES ( :COURSE_ID, :FILE_NAME, :FILE_TYPE, :FILE_DATA, :CREATED_ON )";
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("COURSE_ID", material.getCourseId());
        srcMap.addValue("FILE_NAME", material.getFileName());
        srcMap.addValue("FILE_TYPE", material.getFileType());
        try {
            srcMap.addValue("FILE_DATA", file.getBytes());
        } catch (IOException e) {
            logger.error("{} in method {}", e.getCause() , Thread.currentThread().getStackTrace()[1].getMethodName());
        }
        srcMap.addValue("CREATED_ON", material.getCreatedOn());

        namedParameterJdbcTemplate.update(sql, srcMap, holder, new String[]{"ID"});
        material.setId(holder.getKey().intValue());
        logger.info("Added material for course with Id {}", material.getCourseId());
        return this.getById(holder.getKey().intValue());
    }

    @Override
    public List<Material> getMaterialsByCourseId(int courseId) {
        String sql = "SELECT * FROM MATERIALS WHERE COURSE_ID = :COURSE_ID ORDER BY MATERIAL_ID DESC";
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("COURSE_ID", courseId);

        logger.info("Retrieved all materials for course with Id {}", courseId);
        return namedParameterJdbcTemplate.query(sql, srcMap, (resultSet, rowNum) -> new Material(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getBoolean(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getBytes(7),
                resultSet.getString(8)
        ));
    }

    @Override
    public boolean deleteMaterialById(int id) {
        String sql = "DELETE FROM MATERIALS WHERE MATERIAL_ID = :id";
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("id", id);
        logger.info("Deleting material with Id {}", id);
        return (namedParameterJdbcTemplate.update(sql, srcMap) == 1);
    }

    public Material getById(int id) {
        String sql = "SELECT * FROM MATERIALS WHERE MATERIAL_ID = :ID";
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("ID", id);
        try {
            logger.info("Trying to fetch material with Id {}", id);
            return namedParameterJdbcTemplate.queryForObject(sql, srcMap, (resultSet, rowNum) -> new Material(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getBoolean(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getBytes(7),
                    resultSet.getString(8)
            ));
        } catch (Exception e) {
            logger.error("{} in method {}", e.getCause() , Thread.currentThread().getStackTrace()[1].getMethodName());
            return new Material();
        }
    }
}
