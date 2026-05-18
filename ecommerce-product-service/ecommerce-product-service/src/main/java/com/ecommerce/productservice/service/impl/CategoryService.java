package com.ecommerce.productservice.service.impl;

import com.ecommerce.productservice.dto.request.CategoryFilterForm;
import com.ecommerce.productservice.dto.response.CategoryResponse;
import com.ecommerce.productservice.entity.Category;
import com.ecommerce.productservice.exception.ApplicationException;
import com.ecommerce.productservice.mapper.CategoryMapper;
import com.ecommerce.productservice.repository.ICategoryRepository;
import com.ecommerce.productservice.service.ICategoryService;
import com.ecommerce.productservice.specification.CategorySpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> searchCategory(CategoryFilterForm form) {
        Specification<Category> specification = CategorySpecification.buildWhere(form);
        return categoryMapper.toResponseList(categoryRepository.findAll(specification));
    }

    @Override
    @Transactional
    public CategoryResponse deleteCategoryById(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ApplicationException("Không tìm thấy category."));
        category.setIsDeleted(1);
        return categoryMapper.toResponse(category);
    }

    @Override
    public boolean existsById(String id) {
        return categoryRepository.existsById(id);
    }
}
