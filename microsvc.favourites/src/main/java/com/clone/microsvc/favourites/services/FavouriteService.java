package com.clone.microsvc.favourites.services;

import com.clone.microsvc.favourites.dto.FavouriteDTO;
import com.clone.microsvc.favourites.models.Favourite;

import java.util.List;

public interface FavouriteService {

    FavouriteDTO create (FavouriteDTO favouriteDTO);
    List<FavouriteDTO> findAll();
    FavouriteDTO findById(Long id);
    FavouriteDTO update (Long id, FavouriteDTO favouriteDTO);
    void delete (Long id);

}
