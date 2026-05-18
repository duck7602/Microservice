package com.ecommerce.orderservice.mapper;

import com.ecommerce.orderservice.dto.request.OrderRequest;
import com.ecommerce.orderservice.dto.response.OrderResponse;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.events.OrderCreatedEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-07T15:00:32+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toEntity(OrderRequest orderRequest) {
        if ( orderRequest == null ) {
            return null;
        }

        Order order = new Order();

        order.setCustomerId( orderRequest.getCustomerId() );
        order.setPromotionCode( orderRequest.getPromotionCode() );

        return order;
    }

    @Override
    public OrderResponse toResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId( order.getId() );
        orderResponse.setCustomerId( order.getCustomerId() );
        orderResponse.setStatus( order.getStatus() );
        orderResponse.setTotalAmount( order.getTotalAmount() );
        orderResponse.setPromotionCode( order.getPromotionCode() );
        orderResponse.setIsDeleted( order.getIsDeleted() );
        orderResponse.setCreatedDate( order.getCreatedDate() );
        orderResponse.setCreatedBy( order.getCreatedBy() );
        orderResponse.setLastModifiedDate( order.getLastModifiedDate() );
        orderResponse.setLastModifiedBy( order.getLastModifiedBy() );

        return orderResponse;
    }

    @Override
    public List<Order> toEntityList(List<OrderRequest> orderRequests) {
        if ( orderRequests == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( orderRequests.size() );
        for ( OrderRequest orderRequest : orderRequests ) {
            list.add( toEntity( orderRequest ) );
        }

        return list;
    }

    @Override
    public List<OrderResponse> toResponseList(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderResponse> list = new ArrayList<OrderResponse>( orders.size() );
        for ( Order order : orders ) {
            list.add( toResponse( order ) );
        }

        return list;
    }

    @Override
    public OrderCreatedEvent toEvent(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();

        orderCreatedEvent.setIsDeleted( order.getIsDeleted() );
        orderCreatedEvent.setCreatedBy( order.getCreatedBy() );
        orderCreatedEvent.setCreatedDate( order.getCreatedDate() );
        orderCreatedEvent.setLastModifiedBy( order.getLastModifiedBy() );
        orderCreatedEvent.setLastModifiedDate( order.getLastModifiedDate() );
        orderCreatedEvent.setId( order.getId() );
        orderCreatedEvent.setCustomerId( order.getCustomerId() );
        orderCreatedEvent.setStatus( order.getStatus() );
        orderCreatedEvent.setTotalAmount( order.getTotalAmount() );
        orderCreatedEvent.setPromotionCode( order.getPromotionCode() );

        return orderCreatedEvent;
    }
}
