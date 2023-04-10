package com.clone.microsvc.sub.categories.services;

import com.clone.microsvc.sub.categories.dto.SubCategoryDTO;
import com.clone.microsvc.sub.categories.models.SubCategory;
import com.clone.microsvc.sub.categories.repositories.SubCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoryImp implements SubCategoryService{

    @Autowired
    private SubCategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SubCategoryDTO create(SubCategoryDTO subCategoryDTO) {
        SubCategory subCategory= modelMapper.map(subCategoryDTO, SubCategory.class);
        SubCategory save= categoryRepository.save(subCategory);
        return modelMapper.map(save, SubCategoryDTO.class);
    }

    @Override
    public List<SubCategoryDTO> findAll() {
        List<SubCategory> subCategories= categoryRepository.findAll();
        return subCategories.stream().map(subCategory -> modelMapper.map(subCategory, SubCategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public SubCategoryDTO findById(Long id) {
        SubCategory subCategory= categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No se encontró por el Id " + id));
        return modelMapper.map(subCategory, SubCategoryDTO.class);
    }

    @Override
    public SubCategoryDTO update(Long id, SubCategoryDTO subCategoryDTO) {
        SubCategory subCategory = categoryRepository.getReferenceById(id);
        subCategory.setEnable(subCategoryDTO.getEnable());
        subCategory.setName(subCategoryDTO.getName());
        SubCategory saved= categoryRepository.save(subCategory);
        return modelMapper.map(saved, SubCategoryDTO.class);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
