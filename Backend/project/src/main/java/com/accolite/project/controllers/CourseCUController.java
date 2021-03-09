package com.accolite.project.controllers;

import com.accolite.project.models.Course;
import com.accolite.project.services.ICourseCUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("course-cu")
public class CourseCUController {
    @Autowired
    ICourseCUService iCourseCUService;

    @PostMapping("/create")
    public Course add(@RequestBody Course course) {
        return iCourseCUService.add(course);
    }

    @PutMapping("/update/{id}")
    public Course updateById(@RequestBody Course course, @PathVariable int id) {
        return iCourseCUService.updateById(id, course);
    }

}
