package com.accolite.project.dao;

import com.accolite.project.models.Material;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMaterialDao {
    Material add(Material material, MultipartFile file);

    List<Material> getMaterialsByCourseId(int courseId);
}
