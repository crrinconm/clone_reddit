package com.clone.microsvc.favourites.services;

import com.clone.microsvc.favourites.dto.FavouriteDTO;
import com.clone.microsvc.favourites.models.Favourite;
import com.clone.microsvc.favourites.repositories.FavouriteRepository;
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
class FavouriteServiceImpTest {

    @Mock
    private FavouriteRepository favouriteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FavouriteServiceImp favouriteServiceImp;

    @BeforeEach
    void setUp(){
        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(Favourite.class))).thenReturn(Favourite.builder()
                .id(1L).build());

        Mockito.lenient().when(modelMapper.map(Mockito.any(), Mockito.eq(FavouriteDTO.class))).thenReturn(FavouriteDTO.builder()
                .id(1L).build());

    }
    @Test
    void testCreate(){
        //Generate
        FavouriteDTO favouriteDTO= FavouriteDTO.builder()
                .id(1L).build();

        Favourite favourite= Favourite.builder()
                .id(1L).build();

        //When
        Mockito.when(favouriteRepository.save(favourite)).thenReturn(favourite);

        FavouriteDTO favouriteDTO1= favouriteServiceImp.create(favouriteDTO);

        //Then

        assertEquals(favouriteDTO.getId(), favouriteDTO1.getId());
    }

    @Test
    void testFindAll(){
        //Generate
        Favourite favourite1= Favourite.builder()
                .id(1L).build();

        Favourite favourite2= Favourite.builder()
                .id(1L).build();

        List<Favourite> favourites= Arrays.asList(favourite1, favourite2);

        //When
        Mockito.when(favouriteRepository.findAll()).thenReturn(favourites);

        List<FavouriteDTO> favouriteDTOS= favouriteServiceImp.findAll();

        //Then
        assertEquals(favourites.get(1).getId(), favouriteDTOS.get(1).getId());
    }

    @Test
    void testFindById(){
        //Generate
        Favourite favourite= Favourite.builder()
                .id(1L).build();
        //When
        Mockito.when(favouriteRepository.findById(1l)).thenReturn(Optional.of(favourite));

        FavouriteDTO favouriteDTO= favouriteServiceImp.findById(1L);

        //Then
        assertEquals(favourite.getId(), favouriteDTO.getId());
    }

    @Test void testUpdate(){
        //Generate
        Favourite favourite= Favourite.builder()
                .id(1L).build();

        FavouriteDTO favouriteDTO= FavouriteDTO.builder()
                .id(1L).build();

        //When
        Mockito.when(favouriteRepository.getReferenceById(1L)).thenReturn(favourite);
        Mockito.when(favouriteRepository.save(favourite)).thenReturn(favourite);

        FavouriteDTO favouriteDTO1= favouriteServiceImp.update(1L, favouriteDTO);

        //Then
        assertEquals(favouriteDTO.getId(), favouriteDTO1.getId());
    }

   @Test
   void testDelete(){
       //When
       favouriteRepository.deleteById(1L);
       Mockito.verify(favouriteRepository).deleteById(1L);
   }
}