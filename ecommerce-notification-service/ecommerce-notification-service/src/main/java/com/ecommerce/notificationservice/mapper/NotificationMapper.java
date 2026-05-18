package com.ecommerce.notificationservice.mapper;

import com.ecommerce.notificationservice.dto.request.CampaignRequest;
import com.ecommerce.notificationservice.dto.request.NotificationRequest;
import com.ecommerce.notificationservice.dto.response.CampaignResponse;
import com.ecommerce.notificationservice.dto.response.NotificationResponse;
import com.ecommerce.notificationservice.entity.Campaign;
import com.ecommerce.notificationservice.entity.Notification;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    Notification toEntity(NotificationRequest notificationRequest);
    NotificationResponse toResponse(Notification  notification);
    List<Notification > toEntityList(List<NotificationRequest> notificationRequests);
    List<NotificationResponse> toResponseList(List<Notification> notifications);
}
