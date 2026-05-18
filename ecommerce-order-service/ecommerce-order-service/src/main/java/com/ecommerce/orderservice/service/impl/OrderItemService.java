package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.dto.request.OrderItemFilterForm;
import com.ecommerce.orderservice.dto.response.OrderItemResponse;
import com.ecommerce.orderservice.entity.OrderItem;
import com.ecommerce.orderservice.exception.ApplicationException;
import com.ecommerce.orderservice.mapper.OrderItemMapper;
import com.ecommerce.orderservice.repository.IOrderItemRepository;
import com.ecommerce.orderservice.service.IOrderItemService;
import com.ecommerce.orderservice.specification.OrderItemSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderItemService implements IOrderItemService {

    private final IOrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemResponse> filterOrderItem(OrderItemFilterForm form) {
        Specification<OrderItem> specification = OrderItemSpecification.buildWhere(form);
        return orderItemMapper.toResponseList(orderItemRepository.findAll(specification));
    }

    @Override
    @Transactional
    public OrderItemResponse deletedById(String id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(()-> new ApplicationException("Order Item not found"));
        orderItem.setIsDeleted(1);
        return orderItemMapper.toResponse(orderItem);
    }

//    @Override
//    @Transactional
//    public OrderItemResponse deletedById(String id) {
//        orderItemRepository.softDeletedById(id);
//        OrderItem orderItem = orderItemRepository.findById(id)
//                .orElseThrow(()-> new ApplicationException("Order Item not found"));
//        return orderItemMapper.toResponse(orderItem);
//    }
}
