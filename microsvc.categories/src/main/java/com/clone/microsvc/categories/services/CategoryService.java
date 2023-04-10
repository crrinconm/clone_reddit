package com.clone.microsvc.categories.services;

import com.clone.microsvc.categories.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    //Son metodos que voy a crear para el crud

    //Create
    CategoryDTO create (CategoryDTO categoryDTO);

    //Read
    List<CategoryDTO> findAll();
    CategoryDTO findById(Long id);

    //Update
    CategoryDTO update (Long id, CategoryDTO categoryDTO);

    //Delete
    void delete(Long id);

}
