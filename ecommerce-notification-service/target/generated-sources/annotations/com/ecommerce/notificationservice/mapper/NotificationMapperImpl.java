package com.ecommerce.notificationservice.mapper;

import com.ecommerce.notificationservice.dto.request.NotificationRequest;
import com.ecommerce.notificationservice.dto.response.NotificationResponse;
import com.ecommerce.notificationservice.entity.Notification;
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
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public Notification toEntity(NotificationRequest notificationRequest) {
        if ( notificationRequest == null ) {
            return null;
        }

        Notification notification = new Notification();

        notification.setCampaignId( notificationRequest.getCampaignId() );
        notification.setUserId( notificationRequest.getUserId() );
        notification.setSentAt( notificationRequest.getSentAt() );
        notification.setChannel( notificationRequest.getChannel() );

        return notification;
    }

    @Override
    public NotificationResponse toResponse(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationResponse notificationResponse = new NotificationResponse();

        notificationResponse.setId( notification.getId() );
        notificationResponse.setCampaignId( notification.getCampaignId() );
        notificationResponse.setUserId( notification.getUserId() );
        notificationResponse.setSentAt( notification.getSentAt() );
        notificationResponse.setChannel( notification.getChannel() );
        notificationResponse.setIsDeleted( notification.getIsDeleted() );
        notificationResponse.setCreatedDate( notification.getCreatedDate() );
        notificationResponse.setCreatedBy( notification.getCreatedBy() );
        notificationResponse.setLastModifiedDate( notification.getLastModifiedDate() );
        notificationResponse.setLastModifiedBy( notification.getLastModifiedBy() );

        return notificationResponse;
    }

    @Override
    public List<Notification> toEntityList(List<NotificationRequest> notificationRequests) {
        if ( notificationRequests == null ) {
            return null;
        }

        List<Notification> list = new ArrayList<Notification>( notificationRequests.size() );
        for ( NotificationRequest notificationRequest : notificationRequests ) {
            list.add( toEntity( notificationRequest ) );
        }

        return list;
    }

    @Override
    public List<NotificationResponse> toResponseList(List<Notification> notifications) {
        if ( notifications == null ) {
            return null;
        }

        List<NotificationResponse> list = new ArrayList<NotificationResponse>( notifications.size() );
        for ( Notification notification : notifications ) {
            list.add( toResponse( notification ) );
        }

        return list;
    }
}
