package com.ecommerce.orderservice.mapper;

import com.ecommerce.orderservice.dto.request.OrderRequest;
import com.ecommerce.orderservice.dto.response.OrderResponse;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.events.OrderCreatedEvent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderRequest orderRequest);
    OrderResponse toResponse(Order order);
    List<Order> toEntityList(List<OrderRequest> orderRequests);
    List<OrderResponse> toResponseList(List<Order> orders);
    OrderCreatedEvent toEvent(Order order);
}
