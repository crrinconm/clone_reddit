package com.clone.microsvc.favourites.services;

import com.clone.microsvc.favourites.dto.FavouriteDTO;
import com.clone.microsvc.favourites.models.Favourite;
import com.clone.microsvc.favourites.repositories.FavouriteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavouriteServiceImp implements FavouriteService{
    @Autowired
    private FavouriteRepository favouriteRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FavouriteDTO create(FavouriteDTO favouriteDTO) {
        Favourite favourite= modelMapper.map(favouriteDTO,Favourite.class);
        Favourite saved= favouriteRepository.save(favourite);
        return modelMapper.map(saved, FavouriteDTO.class);
    }

    @Override
    public List<FavouriteDTO> findAll() {
        List<Favourite> favourites= favouriteRepository.findAll();
        return favourites.stream().map(favourite -> modelMapper.map(favourite, FavouriteDTO.class)).collect(Collectors.toList());
    }

    @Override
    public FavouriteDTO findById(Long id) {
        Favourite favourite= favouriteRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No se encontró por el Id" + id));
        return modelMapper.map(favourite, FavouriteDTO.class);
    }

    @Override
    public FavouriteDTO update(Long id, FavouriteDTO favouriteDTO) {
        Favourite favourite= favouriteRepository.getReferenceById(id);
        Favourite saved= favouriteRepository.save(favourite);
        return modelMapper.map(saved, FavouriteDTO.class);
    }

    @Override
    public void delete(Long id) {
        favouriteRepository.deleteById(id);
    }
}
