package com.accolite.project.models;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class FeedbackTest {

    @Test
    public void feedback() {
        String date = new Date(System.currentTimeMillis()).toString();

        Feedback feedback = new Feedback(1, 1, "demoName",
                "demo feedback", 4, date
        );

        Assert.assertEquals(1, feedback.getId());
        Assert.assertEquals(1, feedback.getCourseId());
        Assert.assertEquals("demoName", feedback.getParticipantName());
        Assert.assertEquals("demo feedback", feedback.getFeedbackText());
        Assert.assertEquals(4, feedback.getRating());
        Assert.assertEquals(date, feedback.getCreatedOn());
    }
}
