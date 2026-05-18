package com.ecommerce.notificationservice.consumer;

import com.ecommerce.notificationservice.client.IAuthClient;
import com.ecommerce.notificationservice.client.dto.response.UserResponse;
import com.ecommerce.notificationservice.client.impl.AuthClient;
import com.ecommerce.notificationservice.consumer.dto.OrderCreatedEvent;
import com.ecommerce.notificationservice.service.INotificationService;
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

    private final INotificationService notificationService;
    private final ObjectMapper objectMapper;
    private final IAuthClient authClient;

    @KafkaListener(topics = "order_created")
    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 2000, multiplier = 2))
    public void handleOrderCreatedEvent(String event) throws JsonProcessingException {
        OrderCreatedEvent orderCreatedEvent = objectMapper.readValue(event, OrderCreatedEvent.class);
        log.info("Nhận Message", orderCreatedEvent);

        UserResponse userResponse = authClient.getUser(orderCreatedEvent.getCustomerId());
        notificationService.sendOrderSuccessEmail(userResponse.getEmail(), orderCreatedEvent.getTotalAmount());

        log.info("Success to send Email{}", orderCreatedEvent.getId());
    }
}
