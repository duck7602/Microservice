package com.ecommerce.notificationservice.dto.request;

import com.ecommerce.notificationservice.common.Channel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {

    @NotEmpty
    private String campaignId;

    @NotEmpty
    private String userId;

    @NotNull
    private Instant sentAt;

    @NotNull
    private Channel channel;
}
