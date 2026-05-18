package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.client.IIventoryClient;
import com.ecommerce.orderservice.client.IProductClient;
import com.ecommerce.orderservice.client.IPromotionClient;
import com.ecommerce.orderservice.client.dto.request.InventoryFilterForm;
import com.ecommerce.orderservice.client.dto.request.ProductFilterForm;
import com.ecommerce.orderservice.client.dto.request.PromotionFilterForm;
import com.ecommerce.orderservice.client.dto.response.InventoryResponse;
import com.ecommerce.orderservice.client.dto.response.ProductResponse;
import com.ecommerce.orderservice.client.dto.response.PromotionResponse;
import com.ecommerce.orderservice.common.OrderStatus;
import com.ecommerce.orderservice.dto.request.OrderFilterForm;
import com.ecommerce.orderservice.dto.request.OrderItemRequest;
import com.ecommerce.orderservice.dto.request.OrderRequest;
import com.ecommerce.orderservice.dto.response.OrderResponse;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderItem;
import com.ecommerce.orderservice.events.OrderCreatedEvent;
import com.ecommerce.orderservice.exception.ApplicationException;
import com.ecommerce.orderservice.mapper.OrderMapper;
import com.ecommerce.orderservice.repository.IOrderItemRepository;
import com.ecommerce.orderservice.repository.IOrderRepository;
import com.ecommerce.orderservice.service.IOrderService;
import com.ecommerce.orderservice.specification.OrderSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IOrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final IProductClient productClient;
    private final IPromotionClient promotionClient;
    private final IIventoryClient iIventoryClient;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public List<OrderResponse> getAllOrder() {
        return orderMapper.toResponseList(orderRepository.findAll());
    }

    @Override
    public OrderResponse getOrderById(String id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ApplicationException("Order not found"));
        return orderMapper.toResponse(order);
    }

    @Override
    @Transactional
    public OrderResponse deleteOrderById(String id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Order not found"));
        order.setIsDeleted(1);
        return orderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponse> filterOrder(OrderFilterForm form) {
        Specification<Order> specification = OrderSpecification.buildWhere(form);
        return orderMapper.toResponseList(orderRepository.findAll(specification));
    }

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) throws JsonProcessingException {

        PromotionResponse promotionResponse = null;
        if (orderRequest.getPromotionCode() != null) {
            PromotionFilterForm promotionFilterForm = PromotionFilterForm.builder()
                    .code(orderRequest.getPromotionCode()).build();

            promotionResponse = promotionClient.getPromotion(promotionFilterForm);

            if (promotionResponse == null || promotionResponse.getCode() == null) {
                throw new ApplicationException("Mã giảm giá không hợp lệ");
            }
            if (promotionResponse.getUsageLimit() <= 0) {
                throw new ApplicationException("Mã giảm giá hết lượt sử dụng");
            }

            if (promotionResponse.getStartDate() != null && promotionResponse.getStartDate().isAfter(Instant.now())) {
                throw new ApplicationException("Mã giảm giá chưa bắt đầu ");
            }

            if (promotionResponse.getEndDate() != null && promotionResponse.getEndDate().isBefore(Instant.now())) {
                throw new ApplicationException("Mã giảm giá đã kết thúc");
            }
        }

        List<String> ids = orderRequest.getOrderItemRequests()
                .stream()
                .map(OrderItemRequest::getProductId)
                .toList();

        ProductFilterForm productFilterForm = ProductFilterForm.builder().ids(ids).build();
        InventoryFilterForm inventoryFilterForm = InventoryFilterForm.builder().ids(ids).build();

        List<ProductResponse> productResponses = productClient.getProduct(productFilterForm);
        List<InventoryResponse> inventoryResponses = iIventoryClient.getIventory(inventoryFilterForm);

        Map<String, ProductResponse> productMap = new HashMap<>();
        Map<String, InventoryResponse> inventoryMap = new HashMap<>();

        for (ProductResponse response : productResponses) {
            productMap.put(response.getId(), response);
        }

        for (InventoryResponse response : inventoryResponses) {
            inventoryMap.put(response.getProductId(), response);
        }

        Order order = new Order();
        order.setIsDeleted(0);
        order.setCustomerId(orderRequest.getCustomerId());
        order.setStatus(OrderStatus.NEW.name());
        order.setTotalAmount(0);
        Order orderSaved = orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();
        Integer totalAmount = 0;

        for (OrderItemRequest orderItemRequest : orderRequest.getOrderItemRequests()) {

            ProductResponse productResponse = productMap.get(orderItemRequest.getProductId());
            if (productResponse == null) {
                throw new ApplicationException("Không tồn tại Product");
            }

            Integer price = productResponse.getPrice();

            InventoryResponse inventoryResponse = inventoryMap.get(orderItemRequest.getProductId());
            if (inventoryResponse == null) {
                throw new ApplicationException("Không tồn tại Inventory");
            }

            Integer quantityStock = inventoryResponse.getQuantity();

            if (quantityStock < orderItemRequest.getQuantity()) {
                throw new ApplicationException("Số lượng tồn kho không đủ");
            }

            totalAmount += orderItemRequest.getQuantity() * price;

            OrderItem orderItem = new OrderItem();
            orderItem.setIsDeleted(0);
            orderItem.setOrderId(orderSaved.getId());
            orderItem.setProductId(orderItemRequest.getProductId());
            orderItem.setPrice(price);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItems.add(orderItem);
        }

        if (promotionResponse != null) {
            if (totalAmount < promotionResponse.getMinOrderValue()) {
                throw new ApplicationException("Đơn hàng không đủ điều kiện áp mã giảm giá");
            }

            Integer discount = 0;
            if (promotionResponse.getDiscountType() != null && promotionResponse.getDiscountType().equals("AMOUNT")) {
                discount = promotionResponse.getDiscountValue();
            }
            if (promotionResponse.getDiscountType() != null && promotionResponse.getDiscountType().equals("PERCENT")) {
                discount = promotionResponse.getDiscountValue() * totalAmount/ 100;
            }

            totalAmount = totalAmount - discount;

            if (totalAmount < 0){
                totalAmount = 0;
            }
            orderSaved.setPromotionCode(orderRequest.getPromotionCode());
        }

        orderSaved.setTotalAmount(totalAmount);
        List<OrderItem> createOrderItems = orderItemRepository.saveAll(orderItems);
        Order createOrder = orderRepository.save(orderSaved);

        OrderCreatedEvent event = orderMapper.toEvent(createOrder);
        event.setOrderItemList(createOrderItems);
        String eventString = objectMapper.writeValueAsString(event);
        kafkaTemplate.send("order_created",eventString);
        log.info("Publish new order success to order_created : [{}]", event);

        return orderMapper.toResponse(createOrder);
    }
}
