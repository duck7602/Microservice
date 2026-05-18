package com.ecommerce.orderservice.client.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionResponse {

    private String id;
    private String name;
    private String code;
    private String discountType;
    private Integer discountValue;
    private Integer minOrderValue;
    private Instant startDate;
    private Instant endDate;
    private Integer usageLimit;
    private Integer isDeleted;
    private Instant createdDate;
    private String createdBy;
    private Instant lastModifiedDate;
    private String lastModifiedBy;
}
