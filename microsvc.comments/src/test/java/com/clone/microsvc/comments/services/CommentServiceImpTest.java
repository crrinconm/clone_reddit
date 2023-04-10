package com.clone.microsvc.comments.services;

import com.clone.microsvc.comments.dto.CommentDTO;
import com.clone.microsvc.comments.models.Comment;
import com.clone.microsvc.comments.repositories.CommentRepository;
import jdk.jfr.Category;
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

@ExtendWith(MockitoExtension.class)
class CommentServiceImpTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
     private CommentServiceImp commentServiceImp;

    @BeforeEach
    void setUp(){
        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(Comment.class))).thenReturn(Comment.builder()
                .id(1L)
                .comment("Hola").build());

        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(CommentDTO.class))).thenReturn(CommentDTO.builder()
                .id(1L)
                .comment("Hola").build());
    }

    @Test
    void testCreate(){
        //Generate
        CommentDTO commentDTO= CommentDTO.builder()
                .id(1L)
                .comment("Hola").build();

        Comment comment = Comment.builder()
                .id(1L)
                .comment("Hola").build();

        //When
        Mockito.when(commentRepository.save(comment)).thenReturn(comment);
        CommentDTO commentDTO1= commentServiceImp.create(commentDTO);

        //then

        assertEquals(commentDTO.getId(), commentDTO1.getId());
        assertEquals(commentDTO.getComment(), commentDTO1.getComment());
    }

    @Test
    void testFindByID(){
        //Generate
        Comment comment= Comment.builder()
                .id(1L)
                .comment("Hola").build();

        //When
        Mockito.when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        CommentDTO commentDTO= commentServiceImp.findById(1L);

        //Then
        assertEquals(comment.getComment(), commentDTO.getComment());
        assertNotNull(commentDTO);

    }

    @Test
    void testFindAll(){
        //Generate
        Comment comment1= Comment.builder()
                .id(1L)
                .comment("Hola").build();

        Comment comment2= Comment.builder()
                .id(2L)
                .comment("Hola").build();

        List<Comment> commentList= Arrays.asList(comment1, comment2);

        //When
        Mockito.when(commentRepository.findAll()).thenReturn(commentList);
        List<CommentDTO> commentDTOS= commentServiceImp.findAll();

        //Return

        assertEquals(commentList.size(), commentDTOS.size());
        assertEquals(commentList.get(0).getId(), commentDTOS.get(0).getId());
    }

    @Test
    void testUpdate(){
        //Generate

        Comment comment= Comment.builder()
                .id(1L)
                .comment("Hola").build();


        CommentDTO commentDTO= CommentDTO.builder()
                .id(1L)
                .comment("Hola").build();

        Comment saved= Comment.builder()
                .id(1L)
                .comment(commentDTO.getComment()).build();
        //When

        Mockito.when(commentRepository.getReferenceById(1L)).thenReturn(comment);
        Mockito.when(commentRepository.save(saved)).thenReturn(saved);

        CommentDTO commentDTO1= commentServiceImp.update(1L, commentDTO);

        //Then
        assertEquals(saved.getComment(), commentDTO1.getComment());
        assertEquals(saved.getId(), commentDTO1.getId());         //Preguntar por esta parte

    }

    @Test
    void testDelete(){
        //Generate
        commentRepository.deleteById(1L);
        Mockito.verify(commentRepository).deleteById(1L);

    }
}