package com.accolite.project;

import com.accolite.project.models.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class ModelsData {

    public static User getUserData() {
        User user = new User();

        user.setId(1);
        user.setFirstName("dummy");
        user.setLastName("user");
        user.setEmail("dummy@accolitedigital.com");
        user.setDateOfJoining(new Date(System.currentTimeMillis()).toString());

        return user;
    }

    public static Course getCourseData() {
        Course course = new Course();

        course.setId(1);
        course.setCourseName("c1");
        course.setCourseDescription("d1");
        course.setPreRequisite("p1");
        course.setCreatedOn(new Date(System.currentTimeMillis()).toString());
        course.setLastModifiedOn(new Date(System.currentTimeMillis()).toString());
        course.setCreatedBy(getUserData().getId());

        return course;
    }

    public static Feedback getFeedbackData() {
        Feedback feedback = new Feedback();

        feedback.setId(1);
        feedback.setCourseId(getCourseData().getId());
        feedback.setParticipantName(getUserData().getFirstName());
        feedback.setFeedbackText("demo feedback");
        feedback.setRating(4);
        feedback.setCreatedOn(new Date(System.currentTimeMillis()).toString());

        return feedback;
    }

    public static Skill getSkillData() {
        Skill skill = new Skill();

        skill.setId(1);
        skill.setCourseId(1);
        skill.setSkillName("demo skill");

        return skill;
    }

    public static Material getMaterialData() {
        Material material = new Material();

        material.setId(1);
        material.setCourseId(1);
        material.setParentId(0);
        material.setCurrent(true);
        material.setFileName("demo");
        material.setFileType("application/pdf");
        material.setFileData(null);
        material.setCreatedOn(new Date(System.currentTimeMillis()).toString());

        return material;
    }

    public static MultipartFile getMultipartFile() {
        return new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {

            }
        };
    }
}
