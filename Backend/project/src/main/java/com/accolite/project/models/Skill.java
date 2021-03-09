package com.accolite.project.models;

public class Skill {
    int id;
    int courseId;
    String skillName;

    public Skill(int id, int courseId, String skillName) {
        this.id = id;
        this.courseId = courseId;
        this.skillName = skillName;
    }

    public Skill() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
