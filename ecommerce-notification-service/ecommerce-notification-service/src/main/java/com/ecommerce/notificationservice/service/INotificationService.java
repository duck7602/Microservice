package com.ecommerce.notificationservice.service;

import com.ecommerce.notificationservice.dto.response.NotificationResponse;

import java.util.List;

public interface INotificationService {
    List<NotificationResponse> getAllPromotion();
    void sendOrderSuccessEmail(String toEmail, Integer totalAmount);
}
