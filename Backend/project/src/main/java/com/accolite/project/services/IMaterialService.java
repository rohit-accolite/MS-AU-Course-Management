package com.accolite.project.services;

import com.accolite.project.models.Material;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMaterialService {
    Material add(Material material, MultipartFile file);

    List<Material> getMaterialsByCourseId(int courseId);

    boolean deleteMaterialById(int id);

}
