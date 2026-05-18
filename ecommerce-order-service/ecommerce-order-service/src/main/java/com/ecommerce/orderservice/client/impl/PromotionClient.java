package com.ecommerce.orderservice.client.impl;

import com.ecommerce.orderservice.client.IPromotionClient;
import com.ecommerce.orderservice.client.dto.request.ProductFilterForm;
import com.ecommerce.orderservice.client.dto.request.PromotionFilterForm;
import com.ecommerce.orderservice.client.dto.response.PromotionResponse;
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
public class PromotionClient implements IPromotionClient {
    private final WebClient.Builder webClient;

    @Value("${client.promotion.uri}")
    private String promotionUri;

    @Override
    public PromotionResponse getPromotion(PromotionFilterForm form) {

        BaseResponse<List<PromotionResponse>> response = webClient.build()
                .post()
                .uri(promotionUri)
                .bodyValue(form)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponse<List<PromotionResponse>>>() {})
                .block();

        if (response == null){
            throw new ApplicationException("Không thể kết nối đến Promotion");
        }
        return response.getData().stream().findFirst().orElse(null);
    }
}
