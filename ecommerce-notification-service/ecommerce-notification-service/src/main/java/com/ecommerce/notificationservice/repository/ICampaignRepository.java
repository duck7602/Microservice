package com.ecommerce.notificationservice.repository;

import com.ecommerce.notificationservice.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICampaignRepository extends JpaRepository<Campaign, String>, JpaSpecificationExecutor<Campaign> {
}
