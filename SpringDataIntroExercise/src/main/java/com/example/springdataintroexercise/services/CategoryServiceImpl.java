package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entities.Category;
import com.example.springdataintroexercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {


    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        return this.categoryRepository.getRandomCategories();
    }

    @Override
    public void saveCategory(Category category) {
        this.categoryRepository.save(category);
    }
}
