package com.accolite.project.dao;

import com.accolite.project.models.Skill;
import com.accolite.project.models.User;

public interface IUserDao {

    User add(User user);
    User getUserByEmail(String email);

    User getById(int id);
}
