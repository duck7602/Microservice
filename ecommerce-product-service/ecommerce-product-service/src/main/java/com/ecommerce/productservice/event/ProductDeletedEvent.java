package com.ecommerce.productservice.event;

import com.ecommerce.productservice.entity.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDeletedEvent extends Product {
}
