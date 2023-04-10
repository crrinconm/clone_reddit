package com.clone.microsvc.users.services;

import com.clone.microsvc.users.dto.UserDTO;
import com.clone.microsvc.users.models.User;
import com.clone.microsvc.users.repositories.UserRepository;
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
class UserServiceImpTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImp userServiceImp;

    @BeforeEach
    void setUp(){
        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(User.class))).thenReturn(User.builder()
                .id(1L)
                .email("felipe@gmail.com")
                .username("felipe")
                .password("584556")
                .photo("photo.jpg").build());


        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(UserDTO.class))).thenReturn(UserDTO.builder()
                .id(1L)
                .email("felipe@gmail.com")
                .username("felipe")
                .password("584556")
                .photo("photo.jpg").build());
    }
    @Test
    void testCreate(){
        //Generate

        UserDTO userDTO= UserDTO.builder()
                .id(1L)
                .email("felipe@gmail.com")
                .username("felipe")
                .password("584556")
                .photo("photo.jpg").build();

        User user= User.builder()
                .id(1L)
                .email("felipe@gmail.com")
                .username("felipe")
                .password("584556")
                .photo("photo.jpg").build();

        //When

        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserDTO userDTO1= userServiceImp.create(userDTO);

        //Then

        assertEquals(userDTO.getPassword(), userDTO1.getPassword());
        assertEquals(userDTO.getUsername(), userDTO1.getUsername());
    }
    @Test
    void testFindAll(){
        User user1= User.builder()
                .id(1L)
                .email("felipe@gmail.com")
                .username("felipe")
                .password("584556")
                .photo("photo.jpg").build();

        User user2= User.builder()
                .id(1L)
                .email("felipe@gmail.com")
                .username("felipe")
                .password("584556")
                .photo("photo.jpg").build();

        List<User> users= Arrays.asList(user1, user2);


        //When
        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> userDTOS= userServiceImp.findAll();

        //Then
        assertEquals(users.get(1).getPassword(), userDTOS.get(1).getPassword());
        assertEquals(users.get(1).getEmail(), userDTOS.get(1).getEmail());
    }

    @Test
    void testFindById(){
        //Generate
        User user= User.builder()
                .id(1L)
                .email("felipe@gmail.com")
                .username("felipe")
                .password("584556")
                .photo("photo.jpg").build();

        //When
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO userDTO= userServiceImp.findById(1L);

        //Then
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getId(), userDTO.getId());
    }
    @Test
    void testUpdate(){
        //Generate


        User user= User.builder()
                .id(1L)
                .email("felipe@gmail.com")
                .username("felipe")
                .password("584556")
                .photo("photo.jpg").build();

        UserDTO userDTO= UserDTO.builder()
                .id(1L)
                .email("felipe@gmail.com")
                .username("felipe")
                .password("584556")
                .photo("photo.jpg").build();

        User saved= User.builder()
                .id(1L)
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .photo(userDTO.getPhoto()).build();

        //When
        Mockito.when(userRepository.getReferenceById(1L)).thenReturn(user);
        Mockito.when(userRepository.save(saved)).thenReturn(saved);

        UserDTO userDTO1= userServiceImp.update(1L, userDTO);
        //Then

        assertEquals(saved.getEmail(), userDTO1.getEmail());
        assertEquals(saved.getPassword(), userDTO1.getPassword());
    }

    @Test
    void testDelete(){

        userRepository.deleteById(1L);
        Mockito.verify(userRepository).deleteById(1L);

    }

}