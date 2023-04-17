package com.clone.microsvc.file.posts.services;

import com.clone.microsvc.file.posts.dto.FilePostDTO;
import com.clone.microsvc.file.posts.models.FilePost;
import com.clone.microsvc.file.posts.repositories.FilePostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FilePostImpTest {

    @Mock
    private FilePostRepository filePostRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FilePostImp filePostImp;

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(FilePost.class))).thenReturn(FilePost.builder()
                .id("1L")
                .file("file.jpg").build());

        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(FilePostDTO.class))).thenReturn(FilePostDTO.builder()
                .id("1L")
                .file("file.jpg").build());
    }
    @Test
    void testCreate(){
        //Generate
        FilePostDTO filePostDTO= FilePostDTO.builder()
                .id("1L")
                .file("file.jpg").build();

        FilePost filePost= FilePost.builder()
                .id("1L")
                .file("file.jpg").build();

        //When
        Mockito.when(filePostRepository.save(filePost)).thenReturn(filePost);

        FilePostDTO filePostDTO1= filePostImp.create(filePostDTO);

        //Then

        assertEquals(filePostDTO.getFile(), filePostDTO1.getFile());
        assertEquals(filePostDTO.getId(), filePostDTO1.getId());
    }

    @Test
    void testFindById(){
        //Generate
        FilePost filePost= FilePost.builder()
                .id("1L")
                .file("file.jpg").build();

        //When
        Mockito.when(filePostRepository.findById("1L")).thenReturn(Optional.of(filePost));

        FilePostDTO filePostDTO= filePostImp.findById("1L");

        //Then

        assertEquals(filePost.getFile(), filePostDTO.getFile());
    }

    @Test
    void testFindAll(){
        //Generate
        FilePost filePost1= FilePost.builder()
                .id("1L")
                .file("file.jpg").build();
        FilePost filePost2= FilePost.builder()
                .id("1L")
                .file("file.jpg").build();

        List<FilePost> filePostList= Arrays.asList(filePost1, filePost2);

        //When
        Mockito.when(filePostRepository.findAll()).thenReturn(filePostList);

        List<FilePostDTO> filePostDTOS= filePostImp.findAll();

        //Then
        assertEquals(filePostList.size(), filePostDTOS.size());
        assertEquals(filePostList.get(1).getFile(), filePostDTOS.get(1).getFile());
    }
    @Test
    void testUpdate(){
        //Generate
        FilePost filePost= FilePost.builder()
                .id("1L")
                .file("file.jpg").build();

        FilePostDTO filePostDTO= FilePostDTO.builder()
                .id("1L")
                .file("file.jpg").build();

        FilePost saved= FilePost.builder()
                .id("1L")
                .file(filePostDTO.getFile()).build();

        //When
        Mockito.when(filePostRepository.findById("1L")).thenReturn(Optional.of(filePost));

        Mockito.when(filePostRepository.save(saved)).thenReturn(saved);

        FilePostDTO filePostDTO1= filePostImp.update("1L", filePostDTO);
        //Then
        assertEquals(saved.getFile(),filePostDTO1.getFile() );
    }

    @Test
    void testDelete(){

        filePostRepository.deleteById("1L");
        Mockito.verify(filePostRepository).deleteById("1L");
    }
}