package com.example.LearnQueryOptimization.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.LearnQueryOptimization.dto.request.CategoryRequest;
import com.example.LearnQueryOptimization.dto.response.ApiResponse;
import com.example.LearnQueryOptimization.dto.response.CategoryResponse;
import com.example.LearnQueryOptimization.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CategoryResponse> create(@RequestBody CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Category created successfully")
                .data(categoryService.create(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getById(@PathVariable Long id) {
        return ApiResponse.<CategoryResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Category retrieved successfully")
                .data(categoryService.getById(id))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> getAll() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Categories retrieved successfully")
                .data(categoryService.getAll())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Category updated successfully")
                .data(categoryService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Category deleted successfully")
                .data(null)
                .build();
    }
}

