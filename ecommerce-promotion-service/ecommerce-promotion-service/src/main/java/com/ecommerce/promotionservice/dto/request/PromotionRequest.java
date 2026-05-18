package com.ecommerce.promotionservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionRequest {

    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String code;

    @NotEmpty
    private String discountType;

    @NotNull
    @Positive
    private Integer discountValue;

    @NotNull
    @Positive
    private Integer minOrderValue;

    @NotNull
    private Instant startDate;

    @NotNull
    private Instant endDate;

    @NotNull
    @Positive
    private Integer usageLimit;
}
