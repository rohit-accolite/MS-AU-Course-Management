package com.accolite.project.dao.impl;

import com.accolite.project.dao.IUserDao;
import com.accolite.project.models.User;
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
public class UserDaoImpl implements IUserDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User add(User user) {
        final KeyHolder holder = new GeneratedKeyHolder();
        logger.info("Trying to add user with email {}", user.getEmail());
        String sql = "INSERT INTO USERS ( FIRST_NAME , LAST_NAME , EMAIL , DATE_OF_JOINING ) "
                + " VALUES ( :FIRST_NAME , :LAST_NAME , :EMAIL , :DATE_OF_JOINING )";

        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("FIRST_NAME", user.getFirstName());
        srcMap.addValue("LAST_NAME", user.getLastName());
        srcMap.addValue("EMAIL", user.getEmail());
        srcMap.addValue("DATE_OF_JOINING", user.getDateOfJoining());

        List<User> allUsers = this.getAll();
        for (User u : allUsers) {
            if (u.getEmail().equals(user.getEmail())) {
                logger.info("User already exists");
                return u;
            }
        }

        namedParameterJdbcTemplate.update(sql, srcMap, holder, new String[]{"ID"});
        user.setId(holder.getKey().intValue());
        logger.info("Added user with Id {}", holder.getKey());
        return user;
    }

    @Override
    public User getById(int id) {
        String sql = "SELECT * FROM USERS WHERE USER_ID = :id";

        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("id", id);
        try {
            logger.info("Fetching user with Id {}", id);
            return namedParameterJdbcTemplate.queryForObject(sql, srcMap, (resultSet, rowNum) -> new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        } catch (Exception e) {
            logger.error("{} in method {}", e.getCause(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return new User();
        }
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM USERS";
        logger.info("Fetching all the users");
        return namedParameterJdbcTemplate.query(sql, (resultSet, rowNum) -> new User(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5)
        ));
    }
}
