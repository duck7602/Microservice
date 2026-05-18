package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.request.CategoryRequest;
import com.ecommerce.productservice.dto.response.CategoryResponse;
import com.ecommerce.productservice.entity.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-05T21:48:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( request.getName() );
        category.setParentID( request.getParentID() );

        return category;
    }

    @Override
    public CategoryResponse toResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( category.getId() );
        categoryResponse.setName( category.getName() );
        categoryResponse.setParentID( category.getParentID() );
        categoryResponse.setIsDeleted( category.getIsDeleted() );
        categoryResponse.setCreatedBy( category.getCreatedBy() );
        categoryResponse.setCreatedDate( category.getCreatedDate() );
        categoryResponse.setLastModifiedBy( category.getLastModifiedBy() );
        categoryResponse.setLastModifiedDate( category.getLastModifiedDate() );

        return categoryResponse;
    }

    @Override
    public List<Category> toEntityList(List<CategoryRequest> requests) {
        if ( requests == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( requests.size() );
        for ( CategoryRequest categoryRequest : requests ) {
            list.add( toEntity( categoryRequest ) );
        }

        return list;
    }

    @Override
    public List<CategoryResponse> toResponseList(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryResponse> list = new ArrayList<CategoryResponse>( categories.size() );
        for ( Category category : categories ) {
            list.add( toResponse( category ) );
        }

        return list;
    }
}
