package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.request.CategoryRequest;
import com.ecommerce.productservice.dto.response.CategoryResponse;
import com.ecommerce.productservice.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequest request);
    CategoryResponse toResponse(Category category);
    List<Category> toEntityList(List<CategoryRequest> requests);
    List<CategoryResponse> toResponseList(List<Category> categories);
}
