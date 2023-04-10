package com.clone.microsvc.likes.controllers;

import com.clone.microsvc.likes.dto.LikeDTO;
import com.clone.microsvc.likes.services.LikeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LikeController.class)
class LikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LikeService likeService;

    @BeforeEach
    void setUp(){
        this.objectMapper= new ObjectMapper();
    }

    @Test
    void testFindById() throws Exception{

        //Given
        LikeDTO likeDTO= LikeDTO.builder()
                .id(1L)
                .like(2).build();

        //When
        Mockito.when(likeService.findById(1L)).thenReturn(likeDTO);

        //Then

        mockMvc.perform(get("/api/like/" + likeDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsBytes(likeDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id") .value(1))
                .andExpect((jsonPath("$.like") .value(2)));
    }

    @Test
    void testCreate() throws Exception{

        LikeDTO likeDTO= LikeDTO.builder()
                .id(1L)
                .like(0).build();

        //When

        Mockito.when(likeService.create(likeDTO)).thenReturn(likeDTO);

        //Then

        mockMvc.perform(post("/api/like")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(likeDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id") .value(1))
                .andExpect(jsonPath("$.like") .value(0));
    }

    @Test
    void testUpdate() throws Exception{

        LikeDTO likeDTO= LikeDTO.builder()
                .id(1L)
                .like(5).build();

        //When
        Mockito.when(likeService.update(1L, likeDTO)).thenReturn(likeDTO);

        //Then
        mockMvc.perform(put("/api/like/" + likeDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(likeDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id") .value(1))
                .andExpect(jsonPath("$.like") .value(5));

    }

    @Test
    void testDelete() throws Exception{
        LikeDTO likeDTO= LikeDTO.builder()
                .id(1L)
                .like(0).build();

        mockMvc.perform(delete("/api/like/" + likeDTO.getId()))
                .andExpect(status().isOk());

        Mockito.verify(likeService).delete(1L);
    }


}