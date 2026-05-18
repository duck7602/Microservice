package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.request.ProductRequest;
import com.ecommerce.productservice.dto.response.ProductResponse;
import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.event.ProductCreatedEvent;
import com.ecommerce.productservice.event.ProductDeletedEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-06T19:16:38+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( request.getName() );
        product.setPrice( request.getPrice() );
        product.setCategoryId( request.getCategoryId() );

        return product;
    }

    @Override
    public ProductResponse toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId( product.getId() );
        productResponse.setName( product.getName() );
        productResponse.setPrice( product.getPrice() );
        productResponse.setCategoryId( product.getCategoryId() );
        productResponse.setIsDeleted( product.getIsDeleted() );
        productResponse.setCreatedBy( product.getCreatedBy() );
        productResponse.setCreatedDate( product.getCreatedDate() );
        productResponse.setLastModifiedBy( product.getLastModifiedBy() );
        productResponse.setLastModifiedDate( product.getLastModifiedDate() );

        return productResponse;
    }

    @Override
    public List<Product> toEntityList(List<ProductRequest> requests) {
        if ( requests == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( requests.size() );
        for ( ProductRequest productRequest : requests ) {
            list.add( toEntity( productRequest ) );
        }

        return list;
    }

    @Override
    public List<ProductResponse> toResponseList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductResponse> list = new ArrayList<ProductResponse>( products.size() );
        for ( Product product : products ) {
            list.add( toResponse( product ) );
        }

        return list;
    }

    @Override
    public ProductCreatedEvent toEventCreated(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();

        productCreatedEvent.setIsDeleted( product.getIsDeleted() );
        productCreatedEvent.setCreatedBy( product.getCreatedBy() );
        productCreatedEvent.setCreatedDate( product.getCreatedDate() );
        productCreatedEvent.setLastModifiedBy( product.getLastModifiedBy() );
        productCreatedEvent.setLastModifiedDate( product.getLastModifiedDate() );
        productCreatedEvent.setId( product.getId() );
        productCreatedEvent.setName( product.getName() );
        productCreatedEvent.setPrice( product.getPrice() );
        productCreatedEvent.setCategoryId( product.getCategoryId() );

        return productCreatedEvent;
    }

    @Override
    public List<ProductCreatedEvent> toEventCreatedList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductCreatedEvent> list = new ArrayList<ProductCreatedEvent>( products.size() );
        for ( Product product : products ) {
            list.add( toEventCreated( product ) );
        }

        return list;
    }

    @Override
    public ProductDeletedEvent toEventDeleted(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDeletedEvent productDeletedEvent = new ProductDeletedEvent();

        productDeletedEvent.setIsDeleted( product.getIsDeleted() );
        productDeletedEvent.setCreatedBy( product.getCreatedBy() );
        productDeletedEvent.setCreatedDate( product.getCreatedDate() );
        productDeletedEvent.setLastModifiedBy( product.getLastModifiedBy() );
        productDeletedEvent.setLastModifiedDate( product.getLastModifiedDate() );
        productDeletedEvent.setId( product.getId() );
        productDeletedEvent.setName( product.getName() );
        productDeletedEvent.setPrice( product.getPrice() );
        productDeletedEvent.setCategoryId( product.getCategoryId() );

        return productDeletedEvent;
    }

    @Override
    public List<ProductDeletedEvent> toEventDeletedList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDeletedEvent> list = new ArrayList<ProductDeletedEvent>( products.size() );
        for ( Product product : products ) {
            list.add( toEventDeleted( product ) );
        }

        return list;
    }
}
