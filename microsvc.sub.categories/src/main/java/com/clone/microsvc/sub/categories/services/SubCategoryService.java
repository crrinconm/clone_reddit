package com.clone.microsvc.sub.categories.services;

import com.clone.microsvc.sub.categories.dto.SubCategoryDTO;

import java.util.List;

public interface SubCategoryService {

    SubCategoryDTO create (SubCategoryDTO subCategoryDTO);
    List<SubCategoryDTO> findAll();
    SubCategoryDTO findById(Long id);
    SubCategoryDTO update (Long id, SubCategoryDTO subCategoryDTO);
    void delete (Long id);
}
