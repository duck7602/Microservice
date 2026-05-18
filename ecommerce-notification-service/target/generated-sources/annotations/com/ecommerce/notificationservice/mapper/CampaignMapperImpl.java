package com.ecommerce.notificationservice.mapper;

import com.ecommerce.notificationservice.dto.request.CampaignRequest;
import com.ecommerce.notificationservice.dto.response.CampaignResponse;
import com.ecommerce.notificationservice.entity.Campaign;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-07T03:31:35+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class CampaignMapperImpl implements CampaignMapper {

    @Override
    public Campaign toEntity(CampaignRequest campaignRequest) {
        if ( campaignRequest == null ) {
            return null;
        }

        Campaign campaign = new Campaign();

        campaign.setName( campaignRequest.getName() );
        campaign.setMessage( campaignRequest.getMessage() );
        campaign.setStatus( campaignRequest.getStatus() );

        return campaign;
    }

    @Override
    public CampaignResponse toResponse(Campaign campaign) {
        if ( campaign == null ) {
            return null;
        }

        CampaignResponse campaignResponse = new CampaignResponse();

        campaignResponse.setId( campaign.getId() );
        campaignResponse.setName( campaign.getName() );
        campaignResponse.setMessage( campaign.getMessage() );
        campaignResponse.setStatus( campaign.getStatus() );
        campaignResponse.setIsDeleted( campaign.getIsDeleted() );
        campaignResponse.setCreatedDate( campaign.getCreatedDate() );
        campaignResponse.setCreatedBy( campaign.getCreatedBy() );
        campaignResponse.setLastModifiedDate( campaign.getLastModifiedDate() );
        campaignResponse.setLastModifiedBy( campaign.getLastModifiedBy() );

        return campaignResponse;
    }

    @Override
    public List<Campaign> toEntityList(List<CampaignRequest> campaignRequests) {
        if ( campaignRequests == null ) {
            return null;
        }

        List<Campaign> list = new ArrayList<Campaign>( campaignRequests.size() );
        for ( CampaignRequest campaignRequest : campaignRequests ) {
            list.add( toEntity( campaignRequest ) );
        }

        return list;
    }

    @Override
    public List<CampaignResponse> toResponseList(List<Campaign> campaigns) {
        if ( campaigns == null ) {
            return null;
        }

        List<CampaignResponse> list = new ArrayList<CampaignResponse>( campaigns.size() );
        for ( Campaign campaign : campaigns ) {
            list.add( toResponse( campaign ) );
        }

        return list;
    }
}
