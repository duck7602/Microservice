package com.ecommerce.inventoryservice.consumer;

import com.ecommerce.inventoryservice.consumer.dto.order.OrderCreatedEvent;
import com.ecommerce.inventoryservice.dto.request.LockProductItemRequest;
import com.ecommerce.inventoryservice.dto.request.LockProductRequest;
import com.ecommerce.inventoryservice.service.IInventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreatedConsumer {

    private final IInventoryService inventoryService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order_created")
    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 2000, multiplier = 2))
    public void handleOrderCreatedEvent(String event) throws JsonProcessingException {
        OrderCreatedEvent orderCreatedEvent = objectMapper.readValue(event, OrderCreatedEvent.class);
        log.info("Nhận Message", orderCreatedEvent);

        List<LockProductItemRequest> lockProductItems = new ArrayList<>();

        orderCreatedEvent.getOrderItemList().forEach(orderItem -> {
            LockProductItemRequest lockProductItem = new LockProductItemRequest();
            lockProductItem.setProductId(orderItem.getProductId());
            lockProductItem.setQuantity(orderItem.getQuantity());

            lockProductItems.add(lockProductItem);
        });

        LockProductRequest lockProductReq = new LockProductRequest();
        lockProductReq.setLockProductItemRequests(lockProductItems);
        lockProductReq.setOrderId(orderCreatedEvent.getId());

        inventoryService.lock(lockProductReq);
        log.info("Success to lock product item of {}", orderCreatedEvent.getId());
    }
}
