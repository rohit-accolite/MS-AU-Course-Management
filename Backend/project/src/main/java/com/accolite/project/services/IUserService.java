package com.accolite.project.services;

import com.accolite.project.models.Skill;
import com.accolite.project.models.User;

public interface IUserService {
    User add(User user);

    User getById(int id);
}
