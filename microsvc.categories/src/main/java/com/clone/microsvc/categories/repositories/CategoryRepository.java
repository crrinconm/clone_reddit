package com.clone.microsvc.categories.repositories;


import com.clone.microsvc.categories.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Long> {
}
