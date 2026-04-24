package com.order_service.mapper;

import com.order_service.dto.response.OrderResponse;
import com.order_service.entity.Order;
import com.order_service.events.OrderCreatedEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-15T14:07:20+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponse to(Order order) {
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
    public OrderCreatedEvent toEvent(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();

        orderCreatedEvent.setIsDeleted( order.getIsDeleted() );
        orderCreatedEvent.setCreatedDate( order.getCreatedDate() );
        orderCreatedEvent.setCreatedBy( order.getCreatedBy() );
        orderCreatedEvent.setLastModifiedDate( order.getLastModifiedDate() );
        orderCreatedEvent.setLastModifiedBy( order.getLastModifiedBy() );
        orderCreatedEvent.setId( order.getId() );
        orderCreatedEvent.setCustomerId( order.getCustomerId() );
        orderCreatedEvent.setStatus( order.getStatus() );
        orderCreatedEvent.setTotalAmount( order.getTotalAmount() );
        orderCreatedEvent.setPromotionCode( order.getPromotionCode() );

        return orderCreatedEvent;
    }

    @Override
    public List<Order> toOrders(List<OrderResponse> orderResponse) {
        if ( orderResponse == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( orderResponse.size() );
        for ( OrderResponse orderResponse1 : orderResponse ) {
            list.add( orderResponseToOrder( orderResponse1 ) );
        }

        return list;
    }

    @Override
    public List<OrderResponse> toOrderRes(List<Order> order) {
        if ( order == null ) {
            return null;
        }

        List<OrderResponse> list = new ArrayList<OrderResponse>( order.size() );
        for ( Order order1 : order ) {
            list.add( to( order1 ) );
        }

        return list;
    }

    protected Order orderResponseToOrder(OrderResponse orderResponse) {
        if ( orderResponse == null ) {
            return null;
        }

        Order order = new Order();

        order.setIsDeleted( orderResponse.getIsDeleted() );
        order.setCreatedDate( orderResponse.getCreatedDate() );
        order.setCreatedBy( orderResponse.getCreatedBy() );
        order.setLastModifiedDate( orderResponse.getLastModifiedDate() );
        order.setLastModifiedBy( orderResponse.getLastModifiedBy() );
        order.setId( orderResponse.getId() );
        order.setCustomerId( orderResponse.getCustomerId() );
        order.setStatus( orderResponse.getStatus() );
        order.setTotalAmount( orderResponse.getTotalAmount() );
        order.setPromotionCode( orderResponse.getPromotionCode() );

        return order;
    }
}
