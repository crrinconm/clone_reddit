package com.clone.microsvc.categories.services;


import com.clone.microsvc.categories.dto.CategoryDTO;
import com.clone.microsvc.categories.models.Category;
import com.clone.microsvc.categories.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)                 //Contenedor de Spring lo reconozca como una clase
class CategoryServiceImpTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper; // Agrega un mock para ModelMapper

    @InjectMocks
    private CategoryServiceImp categoryServiceImp;

    @BeforeEach
    void setUp() {
        // Configura la respuesta del mock ModelMapper
        // Te explico aqui el codigo
        //Esto convierte a un entity of Category, con los atributos definidos id, name, etc
        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(Category.class))).thenReturn(Category.builder()
                .id(1L)
                .name("Sports")
                .enable(true)                                   //Todo lo que defina en estos atributos deben ponersen iguales en los test
                .icon("image.jpg")
                .build());
        //DTO
        //Esto convierte de un CategoryDTO, miremos la implementación
        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(CategoryDTO.class))).thenReturn(CategoryDTO.builder()
                .id(1L)
                .name("Sports")
                .enable(true)
                .icon("image.jpg")
                .build());
        // Por eso estos dos objetos deben ser iguales
    }

    @Test
    void testCreate() {
        //Datos de entrada
        //Aqui defino el DTO con los mismos datos cargados anteriormente, con el fin de tener un buen uso del modelMapper
        CategoryDTO categoryDTO= CategoryDTO.builder()
                .id(1L)
                .name("Sports")
                .enable(true)
                .icon("image.jpg").build();

        //Aqui defino los valores del entity del setup

        //Ya entendí, gracias. No pensé que le tenía que dar los valores iguales en el método setUp, por eso los había puesto iguales solo en los datos de entrada

        Category category= Category.builder()
                .id(1L)
                .name("Sports")
                .enable(true)
                .icon("image.jpg").build();

        //When
        Mockito.when(categoryRepository.save(category)).thenReturn(category);

        CategoryDTO categoryDTO1= categoryServiceImp.create(categoryDTO);

        //Return
        assertEquals(categoryDTO.getId(), categoryDTO1.getId());
        assertEquals(categoryDTO.getName(), categoryDTO1.getName());
    }


    @Test
    void testFindAll(){

        //BDD -> GIVEN (Datos de entrada)
        Category category1= Category.builder()
                .id(1L)
                .name("Sports")
                .enable(true)
                .icon("image.jpg").build();

        Category category2= Category.builder()
                .id(2L)
                .name("Sports")
                .enable(false)
                .icon("image.jpg").build();

        List<Category>categories= Arrays.asList(category1, category2);

        //BDD -> WHEN (Ejecute la función)

        Mockito.when(categoryRepository.findAll()).thenReturn(categories); //Simular la base de datos lo que especifiqué en el método


        List<CategoryDTO> categoryDTOS= categoryServiceImp.findAll();       //Invocar el método findAll


        assertEquals(categories.size(),categoryDTOS.size());                //Necesito saber si el tamaño de "la base de datos" es igual al método retorno
        assertEquals(categories.get(0).getId(), categoryDTOS.get(0).getId());
    }

    @Test
    void findById(){
        //Given

        Category category= Category.builder()
                .id(1L)
                .name("Sports")
                .enable(true)
                .icon("image.jpg").build();

        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        CategoryDTO categoryDTO= categoryServiceImp.findById(1L);

        //then

        assertNotNull(categoryDTO);
        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getName(), categoryDTO.getName());  //Pendiente mirar esta parte

    }

    @Test
    void testUpdate(){
        //Given
        Category category= Category.builder()
                .id(1L)
                .name("Sports")
                .enable(true)
                .icon("image.jpg").build();

        CategoryDTO categoryDTO= CategoryDTO.builder()
                .id(1L)
                .name("Sports")
                .enable(true)
                .icon("image.jpg").build();

        Category saved= Category.builder()
                .id(1L)
                .name(categoryDTO.getName())
                .enable(categoryDTO.getEnable())
                .icon(categoryDTO.getIcon()).build();
        //When

        Mockito.when(categoryRepository.getReferenceById(1L)).thenReturn(category);
        Mockito.when(categoryRepository.save(saved)).thenReturn(saved);
        CategoryDTO categoryDTO1 = categoryServiceImp.update(1L, categoryDTO);

        //Then
        assertEquals(saved.getName(), categoryDTO1.getName());
        assertEquals(saved.getIcon(), categoryDTO1.getIcon());

    }

    @Test
    void testDelete(){
    categoryRepository.deleteById(1L);
    Mockito.verify(categoryRepository).deleteById(1L);

    }

}