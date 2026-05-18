package com.ecommerce.promotionservice.consumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    private String id;
    private String customerId;
    private String status;
    private Integer totalAmount;
    private String promotionCode;

}
