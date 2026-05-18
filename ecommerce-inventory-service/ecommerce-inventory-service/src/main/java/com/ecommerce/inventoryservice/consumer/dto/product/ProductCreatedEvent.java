package com.ecommerce.inventoryservice.consumer.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCreatedEvent extends Product{
}
