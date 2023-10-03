package com.exam.service;

import com.exam.domain.Category;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    public ResponseEntity<?>  saveCategory(Category category);
    public ResponseEntity<?>  getAllCategories();
    public ResponseEntity<?>  getCategory(Long categoryId);
    public ResponseEntity<?>  updateCategory(Category category);
}
