package com.accolite.project.controllers;

import com.accolite.project.ModelsData;
import com.accolite.project.models.User;
import com.accolite.project.services.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    IUserService userService;

    @Test
    void addUser() throws Exception {
        User user = ModelsData.getUserData();

        when(userService.add(user)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(user))
        ).andExpect(status().isOk()).andReturn();
    }

    @Test
    void getById() throws Exception {
        User user = ModelsData.getUserData();

        when(userService.getById(user.getId())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/get-by-id/" + user.getId()))
                .andExpect(status().isOk())
                .andReturn();
    }
}
