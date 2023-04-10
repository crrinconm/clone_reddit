package com.clone.microsvc.categories.controllers;

import com.clone.microsvc.categories.dto.CategoryDTO;
import com.clone.microsvc.categories.services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.ResultActions.*;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.JsonPathResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static net.bytebuddy.matcher.ElementMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)  //Es como el @InjectMock que puse para el servicio (nombre de la clase a simular)
class CategoryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CategoryService categoryService;


    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testCreate() throws Exception {

        //Given

        CategoryDTO categoryDTO= CategoryDTO.builder()
                .id(1L)
                .name("Sports")
                .enable(false)
                .icon("icon.jpg").build();
        //When

        Mockito.when(categoryService.create(categoryDTO)).thenReturn(categoryDTO);


        //Then

        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Sports"));


    }

    @Test
    void testFindById() throws Exception{
        //Given
        CategoryDTO categoryDTO= CategoryDTO.builder()
                .id(1L)
                .name("Sports")
                .enable(true).build();

        //When

        Mockito.when(categoryService.findById(1L)).thenReturn(categoryDTO);

        //Then

        mockMvc.perform(get("/api/categories/" + categoryDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath( "$.name").value("Sports"))
                .andExpect(jsonPath("$.id") .value (1));
    }

    @Test
    void testUpdate() throws Exception{
        //Given

        CategoryDTO categoryDTO= CategoryDTO.builder()
                .id(1L)
                .name("Sports")
                .enable(true)
                .icon("icon.png").build();


        //When
        Mockito.when(categoryService.update(1L, categoryDTO)).thenReturn(categoryDTO);

        //Then

        mockMvc.perform(put("/api/categories/update/" + categoryDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name") . value("Sports"))
                .andExpect(jsonPath("$.icon") . value("icon.png"));
    }
    @Test
    void testDelete() throws Exception{

        //Given
        CategoryDTO categoryDTO= CategoryDTO.builder()
                        .id(1L)
                        .name("Sports")
                        .enable(true)
                        .icon("icon.png").build();

    mockMvc.perform(delete("/api/categories/delete/" + categoryDTO.getId()))
            .andExpect(status().isOk());

        Mockito.verify(categoryService).delete(categoryDTO.getId());
    }
}