package com.ecommerce.notificationservice.service.impl;

import com.ecommerce.notificationservice.dto.request.CampaignFilterForm;
import com.ecommerce.notificationservice.dto.response.CampaignResponse;
import com.ecommerce.notificationservice.entity.Campaign;
import com.ecommerce.notificationservice.mapper.CampaignMapper;
import com.ecommerce.notificationservice.repository.ICampaignRepository;
import com.ecommerce.notificationservice.service.ICampaignService;
import com.ecommerce.notificationservice.specification.CampaignSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CampaignService implements ICampaignService {

    private final ICampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;

    @Override
    public List<CampaignResponse> getAllCampaign(CampaignFilterForm form) {
        Specification<Campaign> specification = CampaignSpecification.buildWhere(form);
        return campaignMapper.toResponseList(campaignRepository.findAll(specification));
    }
}
