package com.clone.microsvc.favourites.controllers;

import com.clone.microsvc.favourites.dto.FavouriteDTO;
import com.clone.microsvc.favourites.models.Favourite;
import com.clone.microsvc.favourites.services.FavouriteService;
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
import org.springframework.test.web.servlet.ResultMatcher;

//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(FavouriteController.class)
class FavouriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FavouriteService favouriteService;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testCreate() throws Exception{

        //Given

        FavouriteDTO favouriteDTO= FavouriteDTO.builder()
                .id(1L).build();
        //When

        Mockito.when(favouriteService.create(favouriteDTO)).thenReturn(favouriteDTO);

        //Then
        mockMvc.perform(post("/api/favourites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(favouriteDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id") .value(1));


    }

    @Test
    void testFindById() throws Exception{
        //Given
        FavouriteDTO favouriteDTO= FavouriteDTO.builder()
                .id(1L).build();

        //When
        Mockito.when(favouriteService.findById(1L)).thenReturn(favouriteDTO);

        //Then
        mockMvc.perform(get("/api/favourites/" + favouriteDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(favouriteDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id") .value(1));
    }

    @Test
    void testUpdate() throws Exception{
        //Given
        FavouriteDTO favouriteDTO= FavouriteDTO.builder()
                .id(1L).build();
        //When

        Mockito.when(favouriteService.update(1L, favouriteDTO)).thenReturn(favouriteDTO);

        //Then

        mockMvc.perform(put("/api/favourites/" + favouriteDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(favouriteDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id") .value(1));

    }

    @Test
    void testDelete() throws Exception{
        //Given
        FavouriteDTO favouriteDTO= FavouriteDTO.builder()
                .id(1L).build();

        favouriteService.delete(1L);
        Mockito.verify(favouriteService).delete(1L);
    }
}