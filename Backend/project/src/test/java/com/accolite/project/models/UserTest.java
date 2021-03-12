package com.accolite.project.models;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class UserTest {

    @Test
    public void user() {
        String date = new Date(System.currentTimeMillis()).toString();

        User user = new User(1, "first", "last",
                "demo@email.com", date
        );

        Assert.assertEquals(1, user.getId());
        Assert.assertEquals("first", user.getFirstName());
        Assert.assertEquals("last", user.getLastName());
        Assert.assertEquals("demo@email.com", user.getEmail());
        Assert.assertEquals(date, user.getDateOfJoining());
    }
}
