package com.accolite.project.services.impl;

import com.accolite.project.ModelsData;
import com.accolite.project.dao.impl.UserDaoImpl;
import com.accolite.project.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserServiceImpl.class)
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @MockBean
    UserDaoImpl userDao;

    @Test
    public void addUser() {
        User user = ModelsData.getUserData();

        when(userDao.add(user)).thenReturn(user);

        assertThat(userService.add(user)).isEqualTo(user);
    }

    @Test
    public void getUserById() {
        User user = ModelsData.getUserData();

        when(userDao.getById(user.getId())).thenReturn(user);

        assertThat(userService.getById(user.getId())).isEqualTo(user);
    }
}
