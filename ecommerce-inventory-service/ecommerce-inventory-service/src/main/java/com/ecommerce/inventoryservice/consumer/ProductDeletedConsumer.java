package com.ecommerce.inventoryservice.consumer;

import com.ecommerce.inventoryservice.consumer.dto.product.ProductDeletedEvent;
import com.ecommerce.inventoryservice.service.IInventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDeletedConsumer {

    private final ObjectMapper objectMapper;
    private final IInventoryService inventoryService;

    @KafkaListener(topics = "product_deleted")
    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 2000, multiplier = 2))
    public void handleProductDeletedEvent(String event) throws JsonProcessingException {
        ProductDeletedEvent productDeletedEvent = objectMapper.readValue(event, ProductDeletedEvent.class);
        log.info("Nhận Message");

        inventoryService.deleteInventory(productDeletedEvent.getId());
        log.info("Xóa thành công");
    }
}
