package com.accolite.project.dao.impl;

import com.accolite.project.dao.IUserDao;
import com.accolite.project.models.Course;
import com.accolite.project.models.Skill;
import com.accolite.project.models.User;
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

    @Override
    public User add(User user) {
        final KeyHolder holder = new GeneratedKeyHolder();

        String sql = "INSERT INTO USERS ( FIRST_NAME , LAST_NAME , EMAIL , DATE_OF_JOINING ) "
                + " VALUES ( :FIRST_NAME , :LAST_NAME , :EMAIL , :DATE_OF_JOINING )";

        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("FIRST_NAME", user.getFirstName());
        srcMap.addValue("LAST_NAME", user.getLastName());
        srcMap.addValue("EMAIL", user.getEmail());
        srcMap.addValue("DATE_OF_JOINING", user.getDateOfJoining());

        List<User> allUsers = this.getAll();
        for(User u: allUsers) {
            if(u.getEmail().equals(user.getEmail())) {
                return u;
            }
        }

        namedParameterJdbcTemplate.update(sql, srcMap, holder, new String[]{"ID"});
        user.setId(holder.getKey().intValue());
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        String sql = "SELECT 1 FROM USERS WHERE EMAIL = ':EMAIL'";

        srcMap.addValue("EMAIL", email);
        List<User> old =   namedParameterJdbcTemplate.query(sql, srcMap, (rs, rowNum) -> new User(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5)
        ));

//        User old = null;
//        old = namedParameterJdbcTemplate.query(sql, srcMap (resultSet) -> new User(
//                resultSet.getInt(1);
//                )
//        ){
//            User oldUser = new User();
//
//            oldUser.setId(resultSet.getInt("id"));
//            oldUser.setFirstName(resultSet.getString("FIRST_NAME"));
//            oldUser.setLastName(resultSet.getString("LAST_NAME"));
//            oldUser.setEmail(resultSet.getString("EMAIL"));
//            oldUser.setDateOfJoining(resultSet.getString("DATE_OF_JOINING"));
//
//            return oldUser;
//        });
        return old.get(0);
    }

    @Override
    public User getById(int id) {
        String sql = "SELECT * FROM USERS WHERE USER_ID = :id";
//        System.out.println(id);
        MapSqlParameterSource srcMap = new MapSqlParameterSource();
        srcMap.addValue("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, srcMap, (resultSet, rowNum) -> new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }catch(Exception e) {
            return new User();
        }
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM USERS";
        return namedParameterJdbcTemplate.query(sql, (resultSet, rowNum) -> new User(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5)
        ));
    }
}
