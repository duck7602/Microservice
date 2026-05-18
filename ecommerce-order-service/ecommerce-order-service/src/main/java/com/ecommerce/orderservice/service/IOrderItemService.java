package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.request.OrderItemFilterForm;
import com.ecommerce.orderservice.dto.response.OrderItemResponse;

import java.util.List;

public interface IOrderItemService {
    List<OrderItemResponse> filterOrderItem(OrderItemFilterForm form);

    OrderItemResponse deletedById(String id);
}
