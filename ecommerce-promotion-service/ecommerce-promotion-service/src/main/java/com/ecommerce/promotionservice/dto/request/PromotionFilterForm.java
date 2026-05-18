package com.ecommerce.promotionservice.dto.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionFilterForm {
    private String id;
    private String search;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Instant minStartDate;
    private String code;
}
