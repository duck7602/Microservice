package com.ecommerce.notificationservice.mapper;

import com.ecommerce.notificationservice.dto.request.CampaignRequest;
import com.ecommerce.notificationservice.dto.response.CampaignResponse;
import com.ecommerce.notificationservice.entity.Campaign;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CampaignMapper {

    Campaign toEntity(CampaignRequest campaignRequest);
    CampaignResponse toResponse(Campaign campaign);
    List<Campaign> toEntityList(List<CampaignRequest> campaignRequests);
    List<CampaignResponse> toResponseList(List<Campaign> campaigns);
}
