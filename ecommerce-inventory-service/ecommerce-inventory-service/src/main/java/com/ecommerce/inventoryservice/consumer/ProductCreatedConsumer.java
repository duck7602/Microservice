package com.ecommerce.inventoryservice.consumer;

import com.ecommerce.inventoryservice.consumer.dto.product.ProductCreatedEvent;
import com.ecommerce.inventoryservice.dto.request.InventoryRequest;
import com.ecommerce.inventoryservice.service.IInventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductCreatedConsumer {
    private final IInventoryService inventoryService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "product_created")
    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 2000, multiplier = 2))
    public void handleProductCreatedEvent(String eventString) throws JsonProcessingException {
        ProductCreatedEvent event = objectMapper.readValue(eventString, ProductCreatedEvent.class);

        log.info("Nhận message", event);

        InventoryRequest inventoryRequest = new InventoryRequest();
        inventoryRequest.setProductId(event.getId());

        inventoryService.createInventory(inventoryRequest);
        log.info("Tạo Inventory Thành Công.");
    }
}
