package com.accolite.project.controllers;

import com.accolite.project.models.Material;
import com.accolite.project.services.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("material")
public class MaterialController {
    @Autowired
    IMaterialService iMaterialService;

    @GetMapping("/get-by-course-id/{courseId}")
    public @ResponseBody
    List<Material> getMaterialsByCourseId(@PathVariable int courseId) {
        return iMaterialService.getMaterialsByCourseId(courseId);
    }

    @PostMapping("/add")
    public @ResponseBody
    Material addMaterial(@RequestParam String courseId, @RequestParam String fileName, @RequestParam String fileType, @RequestParam String createdOn, @RequestParam MultipartFile file) {
        Material material = new Material();
        material.setCourseId(Integer.parseInt(courseId));
        material.setFileName(fileName);
        material.setFileType(fileType);
        material.setCreatedOn(createdOn);
        return iMaterialService.add(material, file);
    }

    @DeleteMapping("/delete/{materialId}")
    public boolean deleteCourseById(@PathVariable int materialId) {
        return iMaterialService.deleteMaterialById(materialId);
    }
}
