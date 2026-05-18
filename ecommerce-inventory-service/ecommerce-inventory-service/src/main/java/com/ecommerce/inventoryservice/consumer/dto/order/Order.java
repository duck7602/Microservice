package com.ecommerce.inventoryservice.consumer.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    private String id;
    private String customerId;
    private String status;
    private Integer totalAmount;
    private String promotionCode;
}
