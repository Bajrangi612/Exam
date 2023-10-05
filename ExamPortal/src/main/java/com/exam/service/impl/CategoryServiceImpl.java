package com.exam.service.impl;

import com.exam.controller.UserController;
import com.exam.domain.Category;
import com.exam.response.CategoryRepository;
import com.exam.response.ResponseDomain;
import com.exam.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger log = LogManager.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<?> saveCategory(Category category) {

        Category category1 =  categoryRepository.findByCategoryNameIgnoreCase(category.getCategoryName());
        if(category1== null){
            categoryRepository.save(category);
            log.info("Category added successfully");

            return ResponseDomain.successResponse("Category Created Successfully..");
        }else{
            log.error("Category already exist with same name");
            return ResponseDomain.badRequest("Category already exist with same name");
        }
    }

    @Override
    public ResponseEntity<?> getAllCategories() {
        log.info("CategoryServiceImpl:::getAllCategories ::: getting all categories");
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getCategory(Long categoryId) {
        log.info("CategoryServiceImpl:::getCategories ::: getting categories for category id ::: "+categoryId);

        return new ResponseEntity<>(categoryRepository.findById(categoryId).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long categoryId) {
        log.info("CategoryServiceImpl:::deleteCategory ::: deleting categories for category id ::: "+categoryId);

        categoryRepository.deleteById(categoryId);
        return ResponseDomain.successResponse("Category Deleted Successfully..");
    }

    @Override
    public ResponseEntity<?> updateCategory(Category category) {
        log.info("CategoryServiceImpl:::updateCategory ::: updating categories for category id ::: "+category.getCategoryId());

        categoryRepository.save(category);
        return ResponseDomain.successResponse("Category updated Successfully..");
    }
}
