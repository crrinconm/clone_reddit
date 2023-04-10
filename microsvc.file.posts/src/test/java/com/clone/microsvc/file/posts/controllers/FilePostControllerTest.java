package com.clone.microsvc.file.posts.controllers;

import com.clone.microsvc.file.posts.dto.FilePostDTO;
import com.clone.microsvc.file.posts.services.FilePostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilePostController.class)
class FilePostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FilePostService filePostService;

    @BeforeEach
    void setUp(){
        this.objectMapper= new ObjectMapper();
    }

    @Test
    void testFindById() throws Exception{
        //Given

        FilePostDTO filePostDTO= FilePostDTO.builder()
                .id(1L)
                .file("file.pdf").build();

        //When
        Mockito.when(filePostService.findById(1L)).thenReturn(filePostDTO);

        //Then
        mockMvc.perform(get("/api/filepost/" + filePostDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(filePostDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id") .value(1))
                .andExpect(jsonPath("$.file") .value("file.pdf"));

    }

    @Test
    void testCreate() throws Exception{

        //Given
        FilePostDTO filePostDTO= FilePostDTO.builder()
                .id(1L)
                .file("file.pdf").build();

        //When
        Mockito.when(filePostService.create(filePostDTO)).thenReturn(filePostDTO);

        //Then

        mockMvc.perform(post("/api/filepost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(filePostDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id") .value(1))
                .andExpect(jsonPath("$.file") .value("file.pdf"));

    }

    @Test
    void testUpdate() throws Exception{
        //Given
        FilePostDTO filePostDTO= FilePostDTO.builder()
                .id(1L)
                .file("file.pdf").build();

        //When
        Mockito.when(filePostService.update(1L, filePostDTO)).thenReturn(filePostDTO);

        //Then

        mockMvc.perform(put("/api/filepost/" + filePostDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(filePostDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id") .value(1))
                .andExpect(jsonPath("$.file") .value("file.pdf"));
    }

    @Test
    void testDelete() throws Exception{

        FilePostDTO filePostDTO= FilePostDTO.builder()
                .id(1L)
                .file("file.pdf").build();

        mockMvc.perform(delete("/api/filepost/" + filePostDTO.getId()))
                .andExpect(status().isOk());

        Mockito.verify(filePostService).delete(1L);


    }
}