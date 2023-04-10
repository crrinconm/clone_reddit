package com.clone.microsvc.comments.controllers;

import com.clone.microsvc.comments.dto.CommentDTO;
import com.clone.microsvc.comments.services.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    @BeforeEach
    void setUp(){
        this.objectMapper= new ObjectMapper();
    }

    @Test
    void testFindById() throws Exception {

        //Given

        CommentDTO commentDTO = CommentDTO.builder()
                .id(1L)
                .comment("Hello").build();

        //When
        Mockito.when(commentService.findById(1L)).thenReturn(commentDTO);

        //Then

    mockMvc.perform(get("/api/comment/" + commentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(commentDTO)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.comment") .value("Hello"))
                    .andExpect(jsonPath("$.id") .value(1));


    }

    @Test
    void testCreate() throws Exception{
        //Given
        CommentDTO commentDTO= CommentDTO.builder()
                .id(1L)
                .comment("Hello").build();

        //When
        Mockito.when(commentService.create(commentDTO)).thenReturn(commentDTO);

        //Then

        mockMvc.perform(post("/api/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(commentDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath( "$.comment").value("Hello"))
                .andExpect(jsonPath("$.id") .value (1));

        }

        @Test
        void testUpdate() throws Exception{

        //Given
        CommentDTO commentDTO= CommentDTO.builder()
                .id(1L)
                .comment("Hola").build();

        //When
            Mockito.when(commentService.update(1L, commentDTO)). thenReturn(commentDTO);

        //Then
            mockMvc.perform(put("/api/comment/" + commentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(commentDTO)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id") . value(1))
                    .andExpect(jsonPath("$.comment") . value("Hola"));

    }

    @Test
    void testDelete() throws Exception{

        CommentDTO commentDTO= CommentDTO.builder()
                .id(1L)
                .comment("Hola").build();

        mockMvc.perform(delete("/api/comment/" + commentDTO.getId()))
                .andExpect(status().isOk());

        Mockito.verify(commentService).delete(1L);
    }
}