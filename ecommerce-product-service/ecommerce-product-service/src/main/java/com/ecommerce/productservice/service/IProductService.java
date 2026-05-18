package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.request.ProductFIlterForm;
import com.ecommerce.productservice.dto.request.ProductRequest;
import com.ecommerce.productservice.dto.response.ProductResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;

import java.util.List;

public interface IProductService {
    List<ProductResponse> searchProduct(ProductFIlterForm form);

    ProductResponse deleteProduct(String id) throws JsonProcessingException;

    ProductResponse createProduct(ProductRequest request);

    ProductResponse getById(String id);

    void updateProduct(@Valid ProductRequest productRequest, String id);
}
