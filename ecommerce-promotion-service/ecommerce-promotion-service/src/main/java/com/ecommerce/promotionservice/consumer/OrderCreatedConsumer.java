package com.ecommerce.promotionservice.consumer;

import com.ecommerce.promotionservice.consumer.dto.OrderCreatedEvent;
import com.ecommerce.promotionservice.dto.request.PromotionFilterForm;
import com.ecommerce.promotionservice.service.IPromotionService;
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
public class OrderCreatedConsumer {

    private final IPromotionService promotionService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order_created")
    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 2000, multiplier = 2))
    public void handleOrderCreatedEvent(String event) throws JsonProcessingException {
        OrderCreatedEvent orderCreatedEvent = objectMapper.readValue(event, OrderCreatedEvent.class);
        log.info("Nhận Message");

        PromotionFilterForm promotionFilterForm = PromotionFilterForm.builder().code(orderCreatedEvent.getPromotionCode()).build();
        promotionService.updateUsageLimit(promotionFilterForm);
        log.info("Update thành công");
    }
}
