package com.ecommerce.notificationservice.controller;

import com.ecommerce.notificationservice.common.BaseResponse;
import com.ecommerce.notificationservice.dto.request.CampaignFilterForm;
import com.ecommerce.notificationservice.dto.response.CampaignResponse;
import com.ecommerce.notificationservice.dto.response.NotificationResponse;
import com.ecommerce.notificationservice.service.ICampaignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/campaigns")
@Validated
public class CampaignController {

    private final ICampaignService campaignService;

    @GetMapping()
    public ResponseEntity<BaseResponse<List<CampaignResponse>>> searchCampaign(CampaignFilterForm form){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(campaignService.getAllCampaign(form), "Success"));
    }
}
