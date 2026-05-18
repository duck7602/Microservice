package com.ecommerce.notificationservice.dto.response;

import com.ecommerce.notificationservice.common.Channel;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {

    private String id;
    private String campaignId;
    private String userId;
    private Instant sentAt;
    private Channel channel;
    private Integer isDeleted;
    private Instant createdDate;
    private String createdBy;
    private Instant lastModifiedDate;
    private String lastModifiedBy;
}
