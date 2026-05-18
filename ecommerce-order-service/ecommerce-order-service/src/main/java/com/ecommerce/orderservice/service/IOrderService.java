package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.request.OrderFilterForm;
import com.ecommerce.orderservice.dto.request.OrderRequest;
import com.ecommerce.orderservice.dto.response.OrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IOrderService {
    List<OrderResponse> getAllOrder();

    OrderResponse getOrderById(String id);

    OrderResponse deleteOrderById(String id);

    List<OrderResponse> filterOrder(OrderFilterForm form);

    OrderResponse createOrder(OrderRequest orderRequest) throws JsonProcessingException;
}
