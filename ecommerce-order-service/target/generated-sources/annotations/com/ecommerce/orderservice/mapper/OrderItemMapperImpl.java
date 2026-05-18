package com.ecommerce.orderservice.mapper;

import com.ecommerce.orderservice.dto.request.OrderItemRequest;
import com.ecommerce.orderservice.dto.response.OrderItemResponse;
import com.ecommerce.orderservice.entity.OrderItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-04T23:37:20+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItem toEntity(OrderItemRequest orderItemRequest) {
        if ( orderItemRequest == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setProductId( orderItemRequest.getProductId() );
        orderItem.setQuantity( orderItemRequest.getQuantity() );

        return orderItem;
    }

    @Override
    public OrderItemResponse toResponse(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemResponse orderItemResponse = new OrderItemResponse();

        orderItemResponse.setId( orderItem.getId() );
        orderItemResponse.setOrderId( orderItem.getOrderId() );
        orderItemResponse.setProductId( orderItem.getProductId() );
        orderItemResponse.setPrice( orderItem.getPrice() );
        orderItemResponse.setQuantity( orderItem.getQuantity() );
        orderItemResponse.setIsDeleted( orderItem.getIsDeleted() );
        orderItemResponse.setCreatedDate( orderItem.getCreatedDate() );
        orderItemResponse.setCreatedBy( orderItem.getCreatedBy() );
        orderItemResponse.setLastModifiedDate( orderItem.getLastModifiedDate() );
        orderItemResponse.setLastModifiedBy( orderItem.getLastModifiedBy() );

        return orderItemResponse;
    }

    @Override
    public List<OrderItem> toEntityList(List<OrderItemRequest> orderItemRequests) {
        if ( orderItemRequests == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( orderItemRequests.size() );
        for ( OrderItemRequest orderItemRequest : orderItemRequests ) {
            list.add( toEntity( orderItemRequest ) );
        }

        return list;
    }

    @Override
    public List<OrderItemResponse> toResponseList(List<OrderItem> orderItems) {
        if ( orderItems == null ) {
            return null;
        }

        List<OrderItemResponse> list = new ArrayList<OrderItemResponse>( orderItems.size() );
        for ( OrderItem orderItem : orderItems ) {
            list.add( toResponse( orderItem ) );
        }

        return list;
    }
}
