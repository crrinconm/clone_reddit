package com.clone.microsvc.likes.services;

import com.clone.microsvc.likes.dto.LikeDTO;
import com.clone.microsvc.likes.models.Like;
import com.clone.microsvc.likes.repositories.LikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestComponent;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LikeServiceImpTest {

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LikeServiceImp likeServiceImp;

    @BeforeEach
    void setUp(){
        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(Like.class))).thenReturn(Like.builder()
                .id(1L)
                .like(1).build());

        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(LikeDTO.class))).thenReturn(LikeDTO.builder()
                .id(1L)
                .like(1).build());
    }
    @Test
    void testCreate(){
        //Generate

        LikeDTO likeDTO= LikeDTO.builder()
                .id(1L)
                .like(1).build();


        Like like = Like.builder()
                .id(1L)
                .like(1).build();

        //When
        Mockito.when(likeRepository.save(like)).thenReturn(like);

        LikeDTO likeDTO1= likeServiceImp.create(likeDTO);

        //Then
        assertEquals(likeDTO.getLike(), likeDTO1.getLike());

    }

    @Test
    void testFindAll(){
        //Generate
        Like like1= Like.builder()
                .id(1L)
                .like(1).build();

        Like like2= Like.builder()
                .id(2L)
                .like(1).build();

        List<Like> likes= Arrays.asList(like1, like2);

        //When

        Mockito.when(likeRepository.findAll()).thenReturn(likes);

        List<LikeDTO> likeList= likeServiceImp.findAll();

        //Then
        assertEquals(likes.get(1).getLike(), likeList.get(1).getLike());
    }

    @Test
    void testFindById(){
        //Generate
        Like like= Like.builder()
                .id(1L)
                .like(1).build();

        //When
        Mockito.when(likeRepository.findById(1L)).thenReturn(Optional.of(like));

        LikeDTO likeDTO= likeServiceImp.findById(1L);

        //Then
        assertEquals(like.getLike(), likeDTO.getLike());
    }

    @Test
    void testUpdate(){
        //Generate


        Like like= Like.builder()
                .id(1L)
                .like(1).build();

        LikeDTO likeDTO= LikeDTO.builder()
                .id(1L)
                .like(1).build();

        Like saved= Like.builder()
                .id(1L)
                .like(likeDTO.getLike()).build();

        //When
        Mockito.when(likeRepository.getReferenceById(1L)).thenReturn(like);
        Mockito.when(likeRepository.save(saved)).thenReturn(saved);

        LikeDTO likeDTO1= likeServiceImp.update(1L, likeDTO);

        //Then
        assertEquals(saved.getLike(), likeDTO1.getLike());

    }

    @Test
    void testDelete(){

        likeRepository.deleteById(1L);
        Mockito.verify(likeRepository).deleteById(1L);
    }
}