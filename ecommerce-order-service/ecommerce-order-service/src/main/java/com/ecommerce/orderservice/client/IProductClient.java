package com.ecommerce.orderservice.client;

import com.ecommerce.orderservice.client.dto.request.ProductFilterForm;
import com.ecommerce.orderservice.client.dto.response.ProductResponse;

import java.util.List;

public interface IProductClient {
    List<ProductResponse> getProduct(ProductFilterForm form);
}
