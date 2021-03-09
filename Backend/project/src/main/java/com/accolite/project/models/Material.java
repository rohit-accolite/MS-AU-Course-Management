package com.accolite.project.models;

public class Material {
    int id;
    int courseId;
    int parentId;
    boolean isCurrent;
    String fileName;
    String fileType;
    byte[] fileData;
    String createdOn;

    public Material(int id, int courseId, int parentId, boolean isCurrent, String fileName, String fileType, byte[] fileData, String createdOn) {
        this.id = id;
        this.courseId = courseId;
        this.parentId = parentId;
        this.isCurrent = isCurrent;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
        this.createdOn = createdOn;
    }

    public Material() {

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
