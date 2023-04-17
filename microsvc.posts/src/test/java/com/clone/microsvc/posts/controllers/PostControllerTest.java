package com.clone.microsvc.posts.controllers;

import com.clone.microsvc.posts.dto.PostDTO;
import com.clone.microsvc.posts.services.PostService;
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

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

    @BeforeEach
    void setUp(){
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testFindById() throws Exception{

        //Given
        PostDTO postDTO  = PostDTO.builder()
                .id("1L")
                .title("Superheroes")
                .description("Murdered").build();


        //When
        Mockito.when(postService.findById("1L")).thenReturn(postDTO);

        //Then

        mockMvc.perform(get("/api/post/" + postDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsBytes(postDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id") .value("1L"))
                .andExpect((jsonPath("$.description") .value("Murdered")));
    }

    @Test
    void testCreate() throws Exception{

        PostDTO postDTO  = PostDTO.builder()
                .id("1L")
                .title("Superheroes")
                .description("Murdered").build();

        //When

        Mockito.when(postService.create(postDTO)).thenReturn(postDTO);

        //Then

        mockMvc.perform(post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(postDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id") .value("1L"))
                .andExpect(jsonPath("$.title") .value("Superheroes"));
    }

    @Test
    void testUpdate() throws Exception{

        PostDTO postDTO  = PostDTO.builder()
                .id("1L")
                .title("Superheroes")
                .description("Murdered").build();

        //When
        Mockito.when(postService.update("1L", postDTO)).thenReturn(postDTO);

        //Then
        mockMvc.perform(put("/api/post/" + postDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(postDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id") .value("1L"))
                .andExpect(jsonPath("$.description") .value("Murdered"));

    }

    @Test
    void testDelete() throws Exception{
        PostDTO postDTO  = PostDTO.builder()
                .id("1L")
                .title("Superheroes")
                .description("Murdered").build();

        mockMvc.perform(delete("/api/post/" + postDTO.getId()))
                .andExpect(status().isOk());

        Mockito.verify(postService).delete("1L");
    }


}