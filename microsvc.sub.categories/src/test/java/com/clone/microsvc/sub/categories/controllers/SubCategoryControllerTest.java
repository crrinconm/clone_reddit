package com.clone.microsvc.sub.categories.controllers;

import com.clone.microsvc.sub.categories.dto.SubCategoryDTO;
import com.clone.microsvc.sub.categories.services.SubCategoryService;
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

@WebMvcTest(SubCategoryController.class)
class SubCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SubCategoryService subCategoryService;


    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testCreate() throws Exception {

        //Given

        SubCategoryDTO subCategoryDTO= SubCategoryDTO.builder()
                .id(1L)
                .name("Health")
                .enable(true).build();
        //When

        Mockito.when(subCategoryService.create(subCategoryDTO)).thenReturn(subCategoryDTO);


        //Then

        mockMvc.perform(post("/api/subcategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(subCategoryDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Health"))
                .andExpect(jsonPath("$.enable").value(true));


    }

    @Test
    void testFindById() throws Exception{
        //Given
        SubCategoryDTO subCategoryDTO= SubCategoryDTO.builder()
                .id(1L)
                .name("Health")
                .enable(true).build();

        //When

        Mockito.when(subCategoryService.findById(1L)).thenReturn(subCategoryDTO);

        //Then

        mockMvc.perform(get("/api/subcategory/" + subCategoryDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(subCategoryDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath( "$.name").value("Health"))
                .andExpect(jsonPath("$.enable") .value (true));
    }

    @Test
    void testUpdate() throws Exception{
        //Given

        SubCategoryDTO subCategoryDTO= SubCategoryDTO.builder()
                .id(1L)
                .name("Health")
                .enable(true).build();


        //When
        Mockito.when(subCategoryService.update(1L, subCategoryDTO)).thenReturn(subCategoryDTO);

        //Then

        mockMvc.perform(put("/api/subcategory/" + subCategoryDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(subCategoryDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name") . value("Health"))
                .andExpect(jsonPath("$.enable") . value(true));
    }
    @Test
    void testDelete() throws Exception{

        //Given
        SubCategoryDTO subCategoryDTO= SubCategoryDTO.builder()
                .id(1L)
                .name("Health")
                .enable(true).build();

        mockMvc.perform(delete("/api/subcategory/" + subCategoryDTO.getId()))
                .andExpect(status().isOk());

        Mockito.verify(subCategoryService).delete(1L);
    }
}