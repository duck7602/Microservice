package com.ecommerce.orderservice.client.impl;

import com.ecommerce.orderservice.client.IProductClient;
import com.ecommerce.orderservice.client.dto.request.ProductFilterForm;
import com.ecommerce.orderservice.client.dto.response.ProductResponse;
import com.ecommerce.orderservice.common.BaseResponse;
import com.ecommerce.orderservice.exception.ApplicationException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Component
@Data
@RequiredArgsConstructor
public class ProductClient implements IProductClient {

    private final WebClient.Builder webclient;

    @Value("${client.product.uri}")
    private String productUri;

    @Override
    public List<ProductResponse> getProduct(ProductFilterForm form) {

        BaseResponse<List<ProductResponse>> response = webclient.build()
                .post()
                .uri(productUri)
                .bodyValue(form)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponse<List<ProductResponse>>>() {})
                .block();
        if (response == null){
            throw new ApplicationException("Không gọi được Product Service");
        }

        return response.getData();
    }
}
