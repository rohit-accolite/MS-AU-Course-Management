package com.accolite.project.services.impl;

import com.accolite.project.dao.IUserDao;
import com.accolite.project.models.Skill;
import com.accolite.project.models.User;
import com.accolite.project.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDao iUserDao;

    @Override
    public User add(User user) {
        return iUserDao.add(user);
    }

    @Override
    public User getById(int id) {
        return iUserDao.getById(id);
    }
}
