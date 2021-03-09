package com.accolite.project.controllers;

import com.accolite.project.models.Skill;
import com.accolite.project.models.User;
import com.accolite.project.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    IUserService iUserService;

    @PostMapping("/save")
    public User login(@RequestBody User user) {
        return iUserService.add(user);
    }

    @GetMapping("/get-by-id/{id}")
    public @ResponseBody
    User getUserById(@PathVariable int id) {
        return iUserService.getById(id);
    }

}
