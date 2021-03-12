package com.accolite.project.models;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class SkillTest {

    @Test
    public void skill() {
        Skill skill = new Skill(1, 1, "demoSkill");

        Assert.assertEquals(1, skill.getId());
        Assert.assertEquals(1, skill.getCourseId());
        Assert.assertEquals("demoSkill", skill.getSkillName());
    }
}
