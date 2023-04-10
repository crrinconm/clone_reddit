package com.clone.microsvc.posts.controllers;

import com.clone.microsvc.posts.dto.PostDTO;
import com.clone.microsvc.posts.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findById (@PathVariable ("id") Long id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<PostDTO> create (@Valid @RequestBody PostDTO postDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(postDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> update (@PathVariable("id") Long id, @RequestBody PostDTO postDTO){
        return ResponseEntity.ok(postService.update(id, postDTO));
    }

    @DeleteMapping("/{id}")
    void delete (@PathVariable ("id") Long id){
        postService.delete(id);
    }
}
