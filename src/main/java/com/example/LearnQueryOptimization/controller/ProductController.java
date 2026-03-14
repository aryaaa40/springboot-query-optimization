package com.example.LearnQueryOptimization.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.LearnQueryOptimization.dto.request.ProductRequest;
import com.example.LearnQueryOptimization.dto.response.ApiResponse;
import com.example.LearnQueryOptimization.dto.response.PageResponse;
import com.example.LearnQueryOptimization.dto.response.ProductResponse;
import com.example.LearnQueryOptimization.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductResponse> create(@RequestBody ProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Product created successfully")
                .data(productService.create(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getById(@PathVariable Long id) {
        return ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Product retrieved successfully")
                .data(productService.getById(id))
                .build();
    }

    @GetMapping
    public ApiResponse<PageResponse<ProductResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return ApiResponse.<PageResponse<ProductResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Products retrieved successfully")
                .data(productService.getAll(pageable))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Product updated successfully")
                .data(productService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Product deleted successfully")
                .data(null)
                .build();
    }
}

