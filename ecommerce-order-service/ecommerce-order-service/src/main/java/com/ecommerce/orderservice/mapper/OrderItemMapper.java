package com.ecommerce.orderservice.mapper;

import com.ecommerce.orderservice.dto.request.OrderItemRequest;
import com.ecommerce.orderservice.dto.response.OrderItemResponse;
import com.ecommerce.orderservice.entity.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItem toEntity(OrderItemRequest orderItemRequest);
    OrderItemResponse toResponse(OrderItem orderItem);
    List<OrderItem> toEntityList(List<OrderItemRequest> orderItemRequests);
    List<OrderItemResponse> toResponseList(List<OrderItem> orderItems);
}
