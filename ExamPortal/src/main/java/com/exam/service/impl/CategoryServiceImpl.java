package com.exam.service.impl;

import com.exam.domain.Category;
import com.exam.response.CategoryRepository;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<?> saveCategory(Category category) {
        Category category1 =  categoryRepository.findByCategoryNameIgnoreCase(category.getCategoryName());
        if(category1== null){
            categoryRepository.save(category);
            return new ResponseEntity<>("Category added successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Category already exist with same name", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getCategory(Long categoryId) {
        return new ResponseEntity<>(categoryRepository.findById(categoryId).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateCategory(Category category) {
        categoryRepository.save(category);
        return new ResponseEntity<>("Category added successfully", HttpStatus.OK);
    }
}
