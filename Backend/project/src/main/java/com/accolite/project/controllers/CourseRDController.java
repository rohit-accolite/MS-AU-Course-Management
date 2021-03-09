package com.accolite.project.controllers;

import com.accolite.project.models.Course;
import com.accolite.project.services.ICourseRDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course-rd")
public class CourseRDController {
    @Autowired
    ICourseRDService iCourseRDService;

    @GetMapping("/get-all")
    public @ResponseBody
    List<Course> getAllCourses() {
        return iCourseRDService.getAll();
    }

    @GetMapping("/get-by-id/{id}")
    public @ResponseBody
    Course getCourseById(@PathVariable int id) {
        return iCourseRDService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCourseById(@PathVariable int id) {
        return iCourseRDService.deleteById(id);
    }
}
