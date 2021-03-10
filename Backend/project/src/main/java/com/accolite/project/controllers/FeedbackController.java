package com.accolite.project.controllers;

import com.accolite.project.models.Feedback;
import com.accolite.project.models.Skill;
import com.accolite.project.services.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("feedback")
public class FeedbackController {
    @Autowired
    IFeedbackService iFeedbackService;

    @GetMapping("/get-all")
    public @ResponseBody
    List<Feedback> getAllFeedbacks() {
        return iFeedbackService.getAll();
    }

    @GetMapping("/get-by-course-id/{courseId}")
    public @ResponseBody
    List<Feedback> getFeedBacksByCourseId(@PathVariable int courseId) {
        return iFeedbackService.getByCourseId(courseId);
    }

    @PostMapping("/create")
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        return iFeedbackService.add(feedback);
    }

    @GetMapping("/avg-rating-on-course/{courseId}")
    public @ResponseBody
    String getAvgRatingOnCourse(@PathVariable int courseId) {
        return iFeedbackService.getAvgRatingOnCourse(courseId);
    }
}
