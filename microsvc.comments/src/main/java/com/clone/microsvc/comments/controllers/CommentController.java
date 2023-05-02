package com.clone.microsvc.comments.controllers;

import com.clone.microsvc.comments.dto.CommentDTO;
import com.clone.microsvc.comments.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> findById (@PathVariable ("id") Long id){
        return ResponseEntity.ok(commentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }

    @PostMapping
    public ResponseEntity<CommentDTO> create (@Valid @RequestBody CommentDTO commentDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(commentDTO));
    }

    @PutMapping("/{id}")

    public ResponseEntity<CommentDTO> update (@PathVariable ("id") Long id, @RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(commentService.update(id, commentDTO));
    }

    @DeleteMapping("/{id}")
    void delete (@PathVariable ("id") Long id){
        commentService.delete(id);
    }
}
