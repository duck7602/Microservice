package com.ecommerce.notificationservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String message;

    @NotEmpty
    private String status;
}
