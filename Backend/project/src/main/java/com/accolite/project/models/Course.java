package com.accolite.project.models;

public class Course {
    int id;
    String courseName;
    String courseDescription;
    String preRequisite;
    String createdOn;
    String lastModifiedOn;
    int createdByUser;

    public Course(int id, String courseName, String courseDescription, String preRequisite, String createdOn, String lastModifiesOn, int createdBy) {
        this.id = id;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.preRequisite = preRequisite;
        this.createdOn = createdOn;
        this.lastModifiedOn = lastModifiesOn;
        this.createdByUser = createdBy;
    }

    public Course() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getPreRequisite() {
        return preRequisite;
    }

    public void setPreRequisite(String preRequisite) {
        this.preRequisite = preRequisite;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(String lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public int getCreatedBy() {
        return createdByUser;
    }

    public void setCreatedBy(int createdBy) {
        this.createdByUser = createdBy;
    }
}
