package com.ecommerce.notificationservice.entity;

import com.ecommerce.notificationservice.common.Channel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "campaign_id", length = 36, nullable = false)
    private String campaignId;

    @Column(name = "user_id", length = 36, nullable = false)
    private String userId;

    @Column(name = "sent_at")
    private Instant sentAt;

    @Column(name = "channel", nullable = false)
    @Enumerated(EnumType.STRING)
    private Channel channel;

}
