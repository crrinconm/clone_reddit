package com.clone.microsvc.favourites.repositories;

import com.clone.microsvc.favourites.models.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
}
