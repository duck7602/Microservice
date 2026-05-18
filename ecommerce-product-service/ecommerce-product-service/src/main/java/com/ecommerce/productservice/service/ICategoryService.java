package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.request.CategoryFilterForm;
import com.ecommerce.productservice.dto.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponse> searchCategory(CategoryFilterForm form);

    CategoryResponse deleteCategoryById(String id);
    boolean existsById(String id);
}
