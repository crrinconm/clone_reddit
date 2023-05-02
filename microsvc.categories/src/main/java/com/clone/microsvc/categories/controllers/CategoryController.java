package com.clone.microsvc.categories.controllers;

import com.clone.microsvc.categories.dto.CategoryDTO;
import com.clone.microsvc.categories.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> create (@Valid @RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.create(categoryDTO));
    }

    //Parámetro de ruta
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById (@PathVariable ("id") Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll (){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> update (@PathVariable ("id") Long id, @RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.update(id, categoryDTO));
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable("id") Long id){
        categoryService.delete(id);
    }
}
