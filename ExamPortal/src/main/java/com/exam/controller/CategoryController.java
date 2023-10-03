package com.exam.controller;

import com.exam.domain.Category;
import com.exam.response.CategoryRepository;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }
}
