package com.ecommerce.promotionservice.mapper;

import com.ecommerce.promotionservice.dto.request.PromotionRequest;
import com.ecommerce.promotionservice.dto.response.PromotionResponse;
import com.ecommerce.promotionservice.entity.Promotion;
import com.ecommerce.promotionservice.event.PromotionCreatedEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-07T17:02:57+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class PromotionMapperImpl implements PromotionMapper {

    @Override
    public Promotion toEntity(PromotionRequest promotionRequest) {
        if ( promotionRequest == null ) {
            return null;
        }

        Promotion promotion = new Promotion();

        promotion.setId( promotionRequest.getId() );
        promotion.setName( promotionRequest.getName() );
        promotion.setCode( promotionRequest.getCode() );
        promotion.setDiscountType( promotionRequest.getDiscountType() );
        promotion.setDiscountValue( promotionRequest.getDiscountValue() );
        promotion.setMinOrderValue( promotionRequest.getMinOrderValue() );
        promotion.setStartDate( promotionRequest.getStartDate() );
        promotion.setEndDate( promotionRequest.getEndDate() );
        promotion.setUsageLimit( promotionRequest.getUsageLimit() );

        return promotion;
    }

    @Override
    public PromotionResponse toResponse(Promotion promotion) {
        if ( promotion == null ) {
            return null;
        }

        PromotionResponse promotionResponse = new PromotionResponse();

        promotionResponse.setId( promotion.getId() );
        promotionResponse.setName( promotion.getName() );
        promotionResponse.setCode( promotion.getCode() );
        promotionResponse.setDiscountType( promotion.getDiscountType() );
        promotionResponse.setDiscountValue( promotion.getDiscountValue() );
        promotionResponse.setMinOrderValue( promotion.getMinOrderValue() );
        promotionResponse.setStartDate( promotion.getStartDate() );
        promotionResponse.setEndDate( promotion.getEndDate() );
        promotionResponse.setUsageLimit( promotion.getUsageLimit() );
        promotionResponse.setIsDeleted( promotion.getIsDeleted() );
        promotionResponse.setCreatedDate( promotion.getCreatedDate() );
        promotionResponse.setCreatedBy( promotion.getCreatedBy() );
        promotionResponse.setLastModifiedDate( promotion.getLastModifiedDate() );
        promotionResponse.setLastModifiedBy( promotion.getLastModifiedBy() );

        return promotionResponse;
    }

    @Override
    public List<Promotion> toEntityList(List<PromotionRequest> promotionRequests) {
        if ( promotionRequests == null ) {
            return null;
        }

        List<Promotion> list = new ArrayList<Promotion>( promotionRequests.size() );
        for ( PromotionRequest promotionRequest : promotionRequests ) {
            list.add( toEntity( promotionRequest ) );
        }

        return list;
    }

    @Override
    public List<PromotionResponse> toResponseList(List<Promotion> promotions) {
        if ( promotions == null ) {
            return null;
        }

        List<PromotionResponse> list = new ArrayList<PromotionResponse>( promotions.size() );
        for ( Promotion promotion : promotions ) {
            list.add( toResponse( promotion ) );
        }

        return list;
    }

    @Override
    public void updateEntity(PromotionRequest request, Promotion entity) {
        if ( request == null ) {
            return;
        }

        if ( request.getId() != null ) {
            entity.setId( request.getId() );
        }
        if ( request.getName() != null ) {
            entity.setName( request.getName() );
        }
        if ( request.getCode() != null ) {
            entity.setCode( request.getCode() );
        }
        if ( request.getDiscountType() != null ) {
            entity.setDiscountType( request.getDiscountType() );
        }
        if ( request.getDiscountValue() != null ) {
            entity.setDiscountValue( request.getDiscountValue() );
        }
        if ( request.getMinOrderValue() != null ) {
            entity.setMinOrderValue( request.getMinOrderValue() );
        }
        if ( request.getStartDate() != null ) {
            entity.setStartDate( request.getStartDate() );
        }
        if ( request.getEndDate() != null ) {
            entity.setEndDate( request.getEndDate() );
        }
        if ( request.getUsageLimit() != null ) {
            entity.setUsageLimit( request.getUsageLimit() );
        }
    }

    @Override
    public PromotionCreatedEvent toEvent(Promotion promotion) {
        if ( promotion == null ) {
            return null;
        }

        PromotionCreatedEvent promotionCreatedEvent = new PromotionCreatedEvent();

        promotionCreatedEvent.setIsDeleted( promotion.getIsDeleted() );
        promotionCreatedEvent.setCreatedDate( promotion.getCreatedDate() );
        promotionCreatedEvent.setCreatedBy( promotion.getCreatedBy() );
        promotionCreatedEvent.setLastModifiedDate( promotion.getLastModifiedDate() );
        promotionCreatedEvent.setLastModifiedBy( promotion.getLastModifiedBy() );
        promotionCreatedEvent.setId( promotion.getId() );
        promotionCreatedEvent.setName( promotion.getName() );
        promotionCreatedEvent.setCode( promotion.getCode() );
        promotionCreatedEvent.setDiscountType( promotion.getDiscountType() );
        promotionCreatedEvent.setDiscountValue( promotion.getDiscountValue() );
        promotionCreatedEvent.setMinOrderValue( promotion.getMinOrderValue() );
        promotionCreatedEvent.setStartDate( promotion.getStartDate() );
        promotionCreatedEvent.setEndDate( promotion.getEndDate() );
        promotionCreatedEvent.setUsageLimit( promotion.getUsageLimit() );

        return promotionCreatedEvent;
    }
}
