package com.ecommerce.promotionservice.controller;

import com.ecommerce.promotionservice.common.BaseResponse;
import com.ecommerce.promotionservice.dto.request.PromotionFilterForm;
import com.ecommerce.promotionservice.dto.request.PromotionRequest;
import com.ecommerce.promotionservice.dto.response.PromotionResponse;
import com.ecommerce.promotionservice.service.IPromotionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/promotions")
@Validated
public class PromotionController {

    private final IPromotionService promotionService;

    @PostMapping
    public ResponseEntity<BaseResponse<List<PromotionResponse>>> searchPromotion(@RequestBody PromotionFilterForm form){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(promotionService.searchPromotion(form), "Success"));
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<PromotionResponse>> createPromotion(@RequestBody PromotionRequest request) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(promotionService.createPromotion(request), "Success"));

    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<PromotionResponse>> updatePromotion(@RequestBody PromotionRequest request, @PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(promotionService.updatePromotion(request, id), "Success"));
    }

    @PutMapping()
    public ResponseEntity<BaseResponse<PromotionResponse>> updateUsageLimitByCode(@RequestBody PromotionFilterForm form){
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(promotionService.updateUsageLimit(form), "Success"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<PromotionResponse>> deletePromotionById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(promotionService.deletePromotionById(id), "Success"));
    }
}
