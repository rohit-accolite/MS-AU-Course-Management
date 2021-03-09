package com.accolite.project.services.impl;

import com.accolite.project.dao.IMaterialDao;
import com.accolite.project.models.Material;
import com.accolite.project.services.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MaterialServiceImpl implements IMaterialService {
    @Autowired
    IMaterialDao iMaterialDao;

    @Override
    public Material add(Material material, MultipartFile file) {
        return iMaterialDao.add(material, file);
    }

    @Override
    public List<Material> getMaterialsByCourseId(int courseId) {
        return iMaterialDao.getMaterialsByCourseId(courseId);
    }
}
