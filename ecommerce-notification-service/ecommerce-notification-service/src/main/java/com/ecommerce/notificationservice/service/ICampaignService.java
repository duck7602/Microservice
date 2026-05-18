package com.ecommerce.notificationservice.service;

import com.ecommerce.notificationservice.dto.request.CampaignFilterForm;
import com.ecommerce.notificationservice.dto.response.CampaignResponse;

import java.util.List;

public interface ICampaignService {
    List<CampaignResponse> getAllCampaign(CampaignFilterForm form);
}
