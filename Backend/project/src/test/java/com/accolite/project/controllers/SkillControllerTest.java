package com.accolite.project.controllers;

import com.accolite.project.ModelsData;
import com.accolite.project.models.Skill;
import com.accolite.project.services.ISkillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SkillController.class)
public class SkillControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ISkillService skillService;

    @Test
    void createSkill() throws Exception {
        Skill skill = ModelsData.getSkillData();

        when(skillService.add(skill)).thenReturn(skill);
        mockMvc.perform(MockMvcRequestBuilders.post("/skill/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(skill))
        ).andExpect(status().isOk()).andReturn();
    }

    @Test
    void editSkill() throws Exception {
        Skill skill = ModelsData.getSkillData();

        when(skillService.updateById(skill.getId(), skill)).thenReturn(skill);

        mockMvc.perform(MockMvcRequestBuilders.put("/skill/update/" + skill.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(skill))
        ).andExpect(status().isOk()).andReturn();
    }

    @Test
    void getAllSkills() throws Exception {
        Skill skill = ModelsData.getSkillData();

        List<Skill> skills = new ArrayList<>();
        skills.add(skill);

        when(skillService.getAll()).thenReturn(skills);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/skill/get-all"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result).isNotNull();
    }

    @Test
    void getById() throws Exception {
        Skill skill = ModelsData.getSkillData();

        when(skillService.getById(skill.getId())).thenReturn(skill);

        mockMvc.perform(MockMvcRequestBuilders.get("/skill/get-by-id/" + skill.getId()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getByCourseId() throws Exception {
        Skill skill = ModelsData.getSkillData();

        when(skillService.getById(ModelsData.getCourseData().getId())).thenReturn(skill);

        mockMvc.perform(MockMvcRequestBuilders.get("/skill/get-by-course-id/" + ModelsData.getCourseData().getId()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteById() throws Exception {
        Skill skill = ModelsData.getSkillData();

        when(skillService.deleteById(skill.getId())).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/skill/delete/" + skill.getId()))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("true");
    }
}
