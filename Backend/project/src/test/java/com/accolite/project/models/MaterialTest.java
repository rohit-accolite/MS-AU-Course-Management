package com.accolite.project.models;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class MaterialTest {

    @Test
    public void material() {
        String date = new Date(System.currentTimeMillis()).toString();
        byte[] fileData = "demo file data".getBytes();

        Material material = new Material(1, 1, 0, true,
                "fileName", "fileType", fileData, date
        );

        Assert.assertEquals(1, material.getId());
        Assert.assertEquals(1, material.getCourseId());
        Assert.assertEquals(0, material.getParentId());
        Assert.assertEquals(true, material.isCurrent());
        Assert.assertEquals("fileName", material.getFileName());
        Assert.assertEquals("fileType", material.getFileType());
        Assert.assertEquals(fileData, material.getFileData());
        Assert.assertEquals(date, material.getCreatedOn());

    }
}
