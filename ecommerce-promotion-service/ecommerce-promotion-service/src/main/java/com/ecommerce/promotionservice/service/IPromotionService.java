package com.ecommerce.promotionservice.service;

import com.ecommerce.promotionservice.dto.request.PromotionFilterForm;
import com.ecommerce.promotionservice.dto.request.PromotionRequest;
import com.ecommerce.promotionservice.dto.response.PromotionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IPromotionService {
    List<PromotionResponse> searchPromotion(PromotionFilterForm form);

    PromotionResponse createPromotion(PromotionRequest request) throws JsonProcessingException;

    PromotionResponse updatePromotion(PromotionRequest request, String id);

    PromotionResponse deletePromotionById(String id);

    PromotionResponse updateUsageLimit(PromotionFilterForm form);
}
