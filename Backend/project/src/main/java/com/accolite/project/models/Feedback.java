package com.accolite.project.models;

public class Feedback {
    int id;
    int courseId;
    String participantName;
    String feedbackText;
    String createdOn;

    public Feedback(int id, int courseId, String participantName, String feedbackText, String createdOn) {
        this.id = id;
        this.courseId = courseId;
        this.participantName = participantName;
        this.feedbackText = feedbackText;
        this.createdOn = createdOn;
    }

    public Feedback() {

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

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
