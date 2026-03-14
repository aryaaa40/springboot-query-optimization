package com.example.LearnQueryOptimization.service;

import java.util.List;

import com.example.LearnQueryOptimization.dto.request.CategoryRequest;
import com.example.LearnQueryOptimization.dto.response.CategoryResponse;

public interface CategoryService {

    CategoryResponse create(CategoryRequest request);
    
    CategoryResponse getById(Long id);

    List<CategoryResponse> getAll();

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);
    
}
