package com.accolite.project.services.impl;

import com.accolite.project.ModelsData;
import com.accolite.project.dao.impl.MaterialDaoImpl;
import com.accolite.project.models.Material;
import com.accolite.project.models.Skill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = MaterialServiceImpl.class)
@RunWith(SpringRunner.class)
public class MaterialServiceImplTest {

    @Autowired
    MaterialServiceImpl materialService;

    @MockBean
    MaterialDaoImpl materialDao;

    @Test
    public void getMaterialsByCourse() {
        Material material = ModelsData.getMaterialData();

        List<Material> materials = new ArrayList<>();
        materials.add(material);

        when(materialDao.getMaterialsByCourseId(ModelsData.getCourseData().getId())).thenReturn(materials);

        assertThat(materialService.getMaterialsByCourseId(ModelsData.getCourseData().getId())).isEqualTo(materials);
    }

    @Test
    public void deleteMaterialsById() {
        Material material = ModelsData.getMaterialData();

        when(materialDao.deleteMaterialById(material.getId())).thenReturn(true);

        assertThat(materialService.deleteMaterialById(material.getId())).isTrue();
    }

    @Test
    public void addMaterial() {
        Material material = ModelsData.getMaterialData();
        MultipartFile multipartFile = ModelsData.getMultipartFile();

        when(materialDao.add(material, multipartFile)).thenReturn(material);

        assertThat(materialService.add(material, multipartFile)).isEqualTo(material);
    }


}
