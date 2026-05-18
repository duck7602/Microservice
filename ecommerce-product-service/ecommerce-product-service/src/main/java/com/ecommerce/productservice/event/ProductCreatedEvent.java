package com.ecommerce.productservice.event;

import com.ecommerce.productservice.entity.Product;
import lombok.*;

@Getter
@Setter
@ToString
public class ProductCreatedEvent extends Product {
}
