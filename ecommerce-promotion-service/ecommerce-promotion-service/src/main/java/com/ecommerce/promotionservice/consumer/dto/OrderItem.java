package com.ecommerce.promotionservice.consumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItem {

    private String id;
    private String orderId;
    private String productId;
    private Integer price;
    private Integer quantity;
}
