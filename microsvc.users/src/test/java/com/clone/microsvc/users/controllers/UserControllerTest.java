package com.clone.microsvc.users.controllers;

import com.clone.microsvc.users.dto.UserDTO;
import com.clone.microsvc.users.models.User;
import com.clone.microsvc.users.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;


    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testCreate() throws Exception {

        //Given

        UserDTO userDTO= UserDTO.builder()
                .id(1L)
                .email("felipe@gmail.com")
                .username("felipe")
                .password("584556")
                .photo("photo.jpg").build();
        //When

        Mockito.when(userService.create(userDTO)).thenReturn(userDTO);


        //Then

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("felipe@gmail.com"))
                .andExpect(jsonPath("$.password").value("584556"));


    }

    @Test
    void testFindById() throws Exception{
        //Given
        UserDTO userDTO= UserDTO.builder()
                .id(1L)
                .email("felipe@gmail.com")
                .username("felipe")
                .password("584556")
                .photo("photo.jpg").build();

        //When

        Mockito.when(userService.findById(1L)).thenReturn(userDTO);

        //Then

        mockMvc.perform(get("/api/user/" + userDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath( "$.username").value("felipe"))
                .andExpect(jsonPath("$.photo") .value ("photo.jpg"));
    }

    @Test
    void testUpdate() throws Exception{
        //Given

        UserDTO userDTO= UserDTO.builder()
                .id(1L)
                .email("felipe@gmail.com")
                .username("felipe")
                .password("584556")
                .photo("photo.jpg").build();


        //When
        Mockito.when(userService.update(1L, userDTO)).thenReturn(userDTO);

        //Then

        mockMvc.perform(put("/api/user/" + userDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id") . value(1))
                .andExpect(jsonPath("$.password") . value("584556"));
    }
    @Test
    void testDelete() throws Exception{

        //Given
        UserDTO userDTO= UserDTO.builder()
                .id(1L)
                .email("felipe@gmail.com")
                .username("felipe")
                .password("584556")
                .photo("photo.jpg").build();

        mockMvc.perform(delete("/api/user/" + userDTO.getId()))
                .andExpect(status().isOk());

        Mockito.verify(userService).delete(1L);
    }
}