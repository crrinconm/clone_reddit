package com.clone.microsvc.categories.services;

import com.clone.microsvc.categories.dto.CategoryDTO;
import com.clone.microsvc.categories.models.Category;
import com.clone.microsvc.categories.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class CategoryServiceImp implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper; //Se hizo la inyección para acceder a sus metodos y hacer la conversión

    //En estos metodos vamos a "igual el DTO y la entidad, dicha entidad la vamos a convertir en un DTO para que el ORM se comunique con ella
    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        //Que pasa aqui, lo mismo que tiene el DTO se la pasa a un entity Category y tienen los mismos valores
        Category category= modelMapper.map(categoryDTO, Category.class); //IMPORTANTE: Debemos recibir el parámetro que estamos especificando en el tipo de clase (SIEMPRE), Por eso se hizo la conversión
        Category SavedCategory= categoryRepository.save(category); //Esa clase anterior que convertimos, la guardamos del mismo tipo y se convierte
        return modelMapper.map(SavedCategory,CategoryDTO.class);   //Se convierte la clase Category para que coincida con el tipo de retorno del método
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {
        Category category= categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("category not found with id"+id));
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.getReferenceById(id);
        category.setName(categoryDTO.getName());    //Actualizando a nivel memoria
        category.setIcon(categoryDTO.getIcon());     //Se guardó a nivel memoria
        Category update= categoryRepository.save(category);
        return modelMapper.map(update,CategoryDTO.class);
    }

    @Override
    public void delete(Long id) {
         categoryRepository.deleteById(id);  //Aquí falta escribir si está corriendo

    }
}
