package com.clone.microsvc.favourites.controllers;

import com.clone.microsvc.favourites.dto.FavouriteDTO;
import com.clone.microsvc.favourites.services.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @PostMapping
    public ResponseEntity<FavouriteDTO> create (@RequestBody FavouriteDTO favouriteDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(favouriteService.create(favouriteDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavouriteDTO> findById (@PathVariable ("id") Long id){
        return ResponseEntity.ok(favouriteService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<FavouriteDTO>> findAll (){
        return ResponseEntity.ok(favouriteService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FavouriteDTO> update (@PathVariable("id") Long id, @RequestBody FavouriteDTO favouriteDTO){
        return ResponseEntity.ok(favouriteService.update(id, favouriteDTO));
    }

    @DeleteMapping("/{id}")
    void delete (@PathVariable("id") Long id){
        favouriteService.delete(id);
    }
}
