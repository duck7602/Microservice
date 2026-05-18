package com.ecommerce.orderservice.client.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionFilterForm {
    private String code;
}
