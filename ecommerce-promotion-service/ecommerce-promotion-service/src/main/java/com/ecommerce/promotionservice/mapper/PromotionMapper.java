package com.ecommerce.promotionservice.mapper;

import com.ecommerce.promotionservice.dto.request.PromotionRequest;
import com.ecommerce.promotionservice.dto.response.PromotionResponse;
import com.ecommerce.promotionservice.entity.Promotion;
import com.ecommerce.promotionservice.event.PromotionCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PromotionMapper {

    Promotion toEntity(PromotionRequest promotionRequest);
    PromotionResponse toResponse(Promotion promotion);
    List<Promotion> toEntityList(List<PromotionRequest> promotionRequests);
    List<PromotionResponse> toResponseList(List<Promotion> promotions);
    void updateEntity(PromotionRequest request, @MappingTarget Promotion entity);
    PromotionCreatedEvent toEvent(Promotion promotion);
}
