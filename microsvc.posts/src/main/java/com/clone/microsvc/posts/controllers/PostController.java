package com.clone.microsvc.posts.controllers;

import com.clone.microsvc.posts.dto.PostDTO;
import com.clone.microsvc.posts.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findById (@PathVariable ("id") String id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> findAll(){
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/sub-categories/{id}")         //Aquí le estoy pasando el id de la subcategoría ya creada
    public ResponseEntity<List<PostDTO>> findBySubcategoryId (@PathVariable ("id") Long subCategoryId){
        return ResponseEntity.ok(postService.findBySubCategoryId(subCategoryId));
    }

    @PostMapping
    public ResponseEntity<PostDTO> create (@Valid @RequestBody PostDTO postDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(postDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> update (@PathVariable("id") String id, @RequestBody PostDTO postDTO){
        return ResponseEntity.ok(postService.update(id, postDTO));
    }

    @DeleteMapping("/{id}")
    void delete (@PathVariable ("id") String id){
        postService.delete(id);
    }
}
