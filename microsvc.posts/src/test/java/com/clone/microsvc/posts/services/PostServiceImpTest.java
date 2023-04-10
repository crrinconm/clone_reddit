package com.clone.microsvc.posts.services;

import com.clone.microsvc.posts.dto.PostDTO;
import com.clone.microsvc.posts.models.Post;
import com.clone.microsvc.posts.repositories.PostRepository;
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
class PostServiceImpTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PostServiceImp postServiceImp;

    @BeforeEach
    void setUp(){
        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(Post.class))).thenReturn(Post.builder()
                .id(1L)
                .title("News")
                .description("Murdered").build());


        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(PostDTO.class))).thenReturn(PostDTO.builder()
                .id(1L)
                .title("News")
                .description("Murdered").build());
    }
    @Test
    void testCreate(){
        //Generate

        PostDTO postDTO= PostDTO.builder()
                .id(1L)
                .title("News")
                .description("Murdered").build();

        Post post= Post.builder()
                .id(1L)
                .title("News")
                .description("Murdered").build();

        //When

        Mockito.when(postRepository.save(post)).thenReturn(post);

        PostDTO postDTO1= postServiceImp.create(postDTO);

        //Then

        assertEquals(postDTO.getDescription(), postDTO1.getDescription());
        assertEquals(postDTO.getTitle(), postDTO1.getTitle());
    }
    @Test
    void testFindAll(){
        Post post1 = Post.builder()
                .id(1L)
                .title("News")
                .description("Murdered").build();

        Post post2 = Post.builder()
                .id(2L)
                .title("News")
                .description("Murdered").build();

        List<Post> postList= Arrays.asList(post1, post2);


        //When
        Mockito.when(postRepository.findAll()).thenReturn(postList);

        List<PostDTO> postDTOS= postServiceImp.findAll();

        //Then
        assertEquals(postList.get(1).getDescription(), postDTOS.get(1).getDescription());
    }

    @Test
    void testFindById(){
        //Generate
        Post post= Post.builder()
                .id(1L)
                .title("News")
                .description("Murdered").build();

        //When
        Mockito.when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        PostDTO postDTO= postServiceImp.findById(1L);

        //Then
        assertEquals(post.getTitle(), postDTO.getTitle());
    }
    @Test
    void testUpdate(){
        //Generate


        Post post= Post.builder()
                .id(1L)
                .title("News")
                .description("Murdered").build();

        PostDTO postDTO= PostDTO.builder()
                .id(1L)
                .title("News")
                .description("Murdered").build();

        Post saved= Post.builder()
                .id(1L)
                .title(postDTO.getTitle())
                .description(postDTO.getDescription()).build();

        //When
        Mockito.when(postRepository.getReferenceById(1L)).thenReturn(post);
        Mockito.when(postRepository.save(saved)).thenReturn(saved);

        PostDTO postDTO1= postServiceImp.update(1L, postDTO);
        //Then

        assertEquals(saved.getDescription(), postDTO1.getDescription());
        assertEquals(saved.getTitle(), postDTO1.getTitle());
    }

    @Test
    void testDelete(){

        postRepository.deleteById(1L);
        Mockito.verify(postRepository).deleteById(1L);

    }
}