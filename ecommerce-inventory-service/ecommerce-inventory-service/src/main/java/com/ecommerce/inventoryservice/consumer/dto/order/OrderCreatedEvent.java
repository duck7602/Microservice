package com.ecommerce.inventoryservice.consumer.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCreatedEvent extends Order {
    List<OrderItem> orderItemList;
}
