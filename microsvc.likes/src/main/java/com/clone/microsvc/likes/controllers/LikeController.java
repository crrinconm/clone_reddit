package com.clone.microsvc.likes.controllers;

import com.clone.microsvc.likes.dto.LikeDTO;
import com.clone.microsvc.likes.services.LikeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping("/{id}")
    public ResponseEntity<LikeDTO> findById (@PathVariable ("id") Long id){
        return ResponseEntity.ok(likeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LikeDTO>> findAll(){
        return ResponseEntity.ok(likeService.findAll());
    }
    @PostMapping
    public ResponseEntity<LikeDTO> create (@Valid @RequestBody LikeDTO likeDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(likeService.create(likeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LikeDTO> update (@PathVariable ("id") Long id, @RequestBody LikeDTO likeDTO){
        return ResponseEntity.ok(likeService.update(id, likeDTO));
    }

    @DeleteMapping("/{id}")
    void delete (@PathVariable ("id") Long id){
        likeService.delete(id);
    }
}
