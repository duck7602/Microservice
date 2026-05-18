package com.ecommerce.orderservice.client.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFilterForm {
    List<String> ids;
}
