package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.request.ProductRequest;
import com.ecommerce.productservice.dto.response.ProductResponse;
import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.event.ProductCreatedEvent;
import com.ecommerce.productservice.event.ProductDeletedEvent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequest request);
    ProductResponse toResponse(Product product);
    List<Product> toEntityList(List<ProductRequest> requests);
    List<ProductResponse> toResponseList(List<Product> products);
    ProductCreatedEvent toEventCreated(Product product);
    List<ProductCreatedEvent> toEventCreatedList(List<Product> products);
    ProductDeletedEvent toEventDeleted(Product product);
    List<ProductDeletedEvent> toEventDeletedList(List<Product> products);
}
