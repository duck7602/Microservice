package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.common.BaseResponse;
import com.ecommerce.productservice.dto.request.CategoryFilterForm;
import com.ecommerce.productservice.dto.response.CategoryResponse;
import com.ecommerce.productservice.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/categories")
@Validated
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<CategoryResponse>>> searchCategory(CategoryFilterForm form){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(categoryService.searchCategory(form), "Success"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryResponse>> deleteCategoryById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(categoryService.deleteCategoryById(id), "Success"));
    }
}
