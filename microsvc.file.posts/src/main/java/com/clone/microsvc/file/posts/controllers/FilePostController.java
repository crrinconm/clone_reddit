package com.clone.microsvc.file.posts.controllers;

import com.clone.microsvc.file.posts.dto.FilePostDTO;
import com.clone.microsvc.file.posts.services.FilePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filepost")
public class FilePostController {

    @Autowired
    private FilePostService filePostService;

    @GetMapping("/{id}")
    public ResponseEntity<FilePostDTO> findById (@PathVariable ("id")Long id){
        return ResponseEntity.ok(filePostService.findById(id));
    }

    @PostMapping
    public ResponseEntity<FilePostDTO> create (@RequestBody FilePostDTO filePostDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(filePostService.create(filePostDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilePostDTO> update (@PathVariable ("id") Long id, @RequestBody FilePostDTO filePostDTO){
        return ResponseEntity.ok(filePostService.update(id, filePostDTO));
    }

    @DeleteMapping("/{id}")
    void delete (@PathVariable ("id") Long id){
        filePostService.delete(id);
    }
}
