package com.accolite.project.controllers;

import com.accolite.project.models.Skill;
import com.accolite.project.services.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("skill")
public class SkillController {
    @Autowired
    ISkillService iSkillService;

    @GetMapping("/get-all")
    public @ResponseBody
    List<Skill> getAllSkills() {
        return iSkillService.getAll();
    }

    @GetMapping("/get-by-id/{id}")
    public @ResponseBody
    Skill getSkillById(@PathVariable int id) {
        return iSkillService.getById(id);
    }

    @GetMapping("/get-by-course-id/{courseId}")
    public @ResponseBody
    Skill getSkillByCourseId(@PathVariable int courseId) {
        return iSkillService.getByCourseId(courseId);
    }

    @PostMapping("/create")
    public Skill addSkill(@RequestBody Skill skill) {
        return iSkillService.add(skill);
    }

    @PutMapping("/update/{id}")
    public Skill updateSkillById(@RequestBody Skill skill, @PathVariable int id) {
        return iSkillService.updateById(id, skill);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteSkillById(@PathVariable int id) {
        return iSkillService.deleteById(id);
    }
}
