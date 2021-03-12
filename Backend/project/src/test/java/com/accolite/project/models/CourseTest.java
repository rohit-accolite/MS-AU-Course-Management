package com.accolite.project.models;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class CourseTest {

    @Test
    public void course() {
        String date = new Date(System.currentTimeMillis()).toString();

        Course course = new Course(1, "demo", "desc",
                "pre", date, date, 1
        );
        Assert.assertEquals(1, course.getId());
        Assert.assertEquals("demo", course.getCourseName());
        Assert.assertEquals("desc", course.getCourseDescription());
        Assert.assertEquals("pre", course.getPreRequisite());
        Assert.assertEquals(date, course.getCreatedOn());
        Assert.assertEquals(date, course.getLastModifiedOn());
        Assert.assertEquals(1, course.getCreatedBy());




    }
}
