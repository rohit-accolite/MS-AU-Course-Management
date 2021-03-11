package com.accolite.project.controllers;

import com.accolite.project.ModelsData;
import com.accolite.project.models.Material;
import com.accolite.project.services.IMaterialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MaterialController.class)
public class MaterialControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    IMaterialService materialService;
/*
    @Test
    void addMaterial() throws Exception {
        Material material = ModelsData.getMaterialData();

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("courseId", "1");
        requestParams.add("fileName", "demo");
        requestParams.add("fileType", "application/pdf");
        requestParams.add("createdOn", new Date(System.currentTimeMillis()).toString());
        requestParams.add("file", "sdjkbcjkdbv");

        when(materialService.add(material, ModelsData.getMultipartFile())).thenReturn(material);
        mockMvc.perform(MockMvcRequestBuilders.post("/material/add")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .params(requestParams)
        ).andExpect(status().isOk()).andReturn();
    }
    https://stackoverflow.com/questions/17972428/mock-mvc-add-request-parameter-to-test
*/
    @Test
    void getByCourseId() throws Exception {
        Material material = ModelsData.getMaterialData();

        List<Material> materials = new ArrayList<>();
        materials.add(material);

        when(materialService.getMaterialsByCourseId(ModelsData.getCourseData().getId())).thenReturn(materials);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/material/get-by-course-id/" + ModelsData.getCourseData().getId()))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result).isNotNull();
    }

    @Test
    void deleteById() throws Exception {
        Material material = ModelsData.getMaterialData();

        when(materialService.deleteMaterialById(material.getId())).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/material/delete/" + material.getId()))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("true");
    }
}
