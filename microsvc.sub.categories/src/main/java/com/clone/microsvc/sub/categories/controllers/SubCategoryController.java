package com.clone.microsvc.sub.categories.controllers;

import com.clone.microsvc.sub.categories.dto.SubCategoryDTO;
import com.clone.microsvc.sub.categories.services.SubCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategory")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/{id}")
    public ResponseEntity<SubCategoryDTO> findByiD(@PathVariable ("id") Long id){
        return ResponseEntity.ok(subCategoryService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubCategoryDTO>> findAll(){
        return ResponseEntity.ok(subCategoryService.findAll());
    }
    @PostMapping
    public ResponseEntity<SubCategoryDTO> create (@Valid @RequestBody SubCategoryDTO subCategoryDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(subCategoryService.create(subCategoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategoryDTO> update (@PathVariable ("id") Long id, @RequestBody SubCategoryDTO subCategoryDTO){
        return ResponseEntity.ok(subCategoryService.update(id, subCategoryDTO));
    }

    @DeleteMapping("/{id}")
    void delete (@PathVariable ("id") Long id){
        subCategoryService.delete(id);
    }
}
