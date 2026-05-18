package com.ecommerce.orderservice.client;

import com.ecommerce.orderservice.client.dto.request.ProductFilterForm;
import com.ecommerce.orderservice.client.dto.request.PromotionFilterForm;
import com.ecommerce.orderservice.client.dto.response.PromotionResponse;

public interface IPromotionClient {
    PromotionResponse getPromotion(PromotionFilterForm form);
}
