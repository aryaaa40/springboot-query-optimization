package com.example.LearnQueryOptimization.service;

import org.springframework.data.domain.Pageable;

import com.example.LearnQueryOptimization.dto.request.ProductRequest;
import com.example.LearnQueryOptimization.dto.response.PageResponse;
import com.example.LearnQueryOptimization.dto.response.ProductResponse;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse getById(Long id);
    PageResponse<ProductResponse> getAll(Pageable pageable);
    ProductResponse update(Long id, ProductRequest request);
    void delete(Long id);
}

