package com.clone.microsvc.sub.categories.services;

import com.clone.microsvc.sub.categories.dto.SubCategoryDTO;
import com.clone.microsvc.sub.categories.models.SubCategory;
import com.clone.microsvc.sub.categories.repositories.SubCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SubCategoryImpTest {

    @Mock
    private SubCategoryRepository subCategoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SubCategoryImp subCategoryImp;

    @BeforeEach
    void setUp(){
        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(SubCategory.class))).thenReturn(SubCategory.builder()
                .id(1L)
                .name("Voleyball")
                .enable(true).build());


        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(SubCategoryDTO.class))).thenReturn(SubCategoryDTO.builder()
                .id(1L)
                .name("Voleyball")
                .enable(true).build());
    }
    @Test
    void testCreate(){
        //Generate

        SubCategoryDTO subCategoryDTO= SubCategoryDTO.builder()
                .id(1L)
                .name("Voleyball")
                .enable(true).build();

        SubCategory subCategory= SubCategory.builder()
                .id(1L)
                .name("Voleyball")
                .enable(true).build();

        //When

        Mockito.when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);

        SubCategoryDTO subCategoryDTO1= subCategoryImp.create(subCategoryDTO);

        //Then

        assertEquals(subCategoryDTO.getName(), subCategoryDTO1.getName());
    }
    @Test
    void testFindAll(){
        SubCategory subCategory1= SubCategory.builder()
                .id(1L)
                .name("Voleyball")
                .enable(true).build();

        SubCategory subCategory2= SubCategory.builder()
                .id(1L)
                .name("Voleyball")
                .enable(true).build();

        List<SubCategory> subCategories= Arrays.asList(subCategory1, subCategory2);


        //When
        Mockito.when(subCategoryRepository.findAll()).thenReturn(subCategories);

        List<SubCategoryDTO> subCategoryDTOS= subCategoryImp.findAll();

        //Then
        assertEquals(subCategories.get(0).getName(), subCategoryDTOS.get(0).getName());
    }

    @Test
    void testFindById(){
        //Generate
        SubCategory subCategory= SubCategory.builder()
                .id(1L)
                .name("Voleyball")
                .enable(true).build();

        //When
        Mockito.when(subCategoryRepository.findById(1L)).thenReturn(Optional.of(subCategory));

        SubCategoryDTO subCategoryDTO= subCategoryImp.findById(1L);

        //Then
        assertEquals(subCategory.getEnable(), subCategoryDTO.getEnable());
    }
    @Test
    void testUpdate(){
        //Generate


        SubCategory subCategory= SubCategory.builder()
                .id(1L)
                .name("Voleyball")
                .enable(true).build();

        SubCategoryDTO subCategoryDTO= SubCategoryDTO.builder()
                .id(1L)
                .name("Voleyball")
                .enable(true).build();

        SubCategory saved= SubCategory.builder()
                .id(1L)
                .name(subCategoryDTO.getName())
                .enable(subCategoryDTO.getEnable()).build();

        //When
        Mockito.when(subCategoryRepository.getReferenceById(1L)).thenReturn(subCategory);
        Mockito.when(subCategoryRepository.save(saved)).thenReturn(saved);

        SubCategoryDTO subCategoryDTO1= subCategoryImp.update(1L, subCategoryDTO);
        //Then

        assertEquals(saved.getName(), subCategoryDTO1.getName());
        assertEquals(saved.getEnable(), subCategoryDTO1.getEnable());
    }

    @Test
    void testDelete(){

        subCategoryRepository.deleteById(1L);
        Mockito.verify(subCategoryRepository).deleteById(1L);

    }
}
