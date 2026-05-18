package com.ecommerce.promotionservice.service.impl;

import com.ecommerce.promotionservice.dto.request.PromotionFilterForm;
import com.ecommerce.promotionservice.dto.request.PromotionRequest;
import com.ecommerce.promotionservice.dto.response.PromotionResponse;
import com.ecommerce.promotionservice.entity.Promotion;
import com.ecommerce.promotionservice.event.PromotionCreatedEvent;
import com.ecommerce.promotionservice.exception.ApplicationException;
import com.ecommerce.promotionservice.mapper.PromotionMapper;
import com.ecommerce.promotionservice.repository.IPromotionRepository;
import com.ecommerce.promotionservice.service.IPromotionService;
import com.ecommerce.promotionservice.specification.PromotionSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PromotionService implements IPromotionService {

    private final IPromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public List<PromotionResponse> searchPromotion(PromotionFilterForm form) {
        Specification<Promotion> specification = PromotionSpecification.buildWhere(form);
        return promotionMapper.toResponseList(promotionRepository.findAll(specification));
    }

    @Override
    public PromotionResponse createPromotion(PromotionRequest request) throws JsonProcessingException {

        // validate business
        if (promotionRepository.existsByCode(request.getCode())) {
            throw new ApplicationException("Promotion code đã tồn tại");
        }

        if (request.getStartDate().isAfter(request.getEndDate())) {
            throw new ApplicationException("Ngày bắt đầu phải trước ngày kết thúc");
        }

        // normalize
        request.setCode(request.getCode().toUpperCase());

        Promotion promotion = promotionMapper.toEntity(request);

        // default
        promotion.setIsDeleted(0);

        // kafka
        PromotionCreatedEvent promotionCreatedEvent = promotionMapper.toEvent(promotion);
        String event = objectMapper.writeValueAsString(promotionCreatedEvent);
        kafkaTemplate.send("promotion_created", event);

        return promotionMapper.toResponse(promotionRepository.save(promotion));

    }

    @Override
    @Transactional
    public PromotionResponse updatePromotion(PromotionRequest request, String id) {

        // validate
        Promotion existing = promotionRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Không tìm thấy Promotion"));

        Instant start = request.getStartDate() != null ? request.getStartDate() : existing.getStartDate();
        Instant end = request.getEndDate() != null ? request.getEndDate() : existing.getEndDate();

        if (end.isBefore(start)) {
            throw new ApplicationException("Ngày kết thúc phải sau ngày bắt đầu");
        }

        if (request.getCode() != null) {
            String code = request.getCode().toUpperCase();
            request.setCode(code);

            if (promotionRepository.existsByCodeAndIdNot(code, id)) {
                throw new ApplicationException("Promotion Code đã tồn tại");
            }
        }
        promotionMapper.updateEntity(request, existing);
        return promotionMapper.toResponse(existing);
    }

    @Override
    public PromotionResponse deletePromotionById(String id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Promotion"));

        promotion.setIsDeleted(1);
        return promotionMapper.toResponse(promotion);
    }

    @Override
    @Transactional
    public PromotionResponse updateUsageLimit(PromotionFilterForm form) {

        if (form.getCode() == null) {
            throw new ApplicationException("Code không được để trống");
        }

        int updatedRows = promotionRepository.decrementUsageLimit(form.getCode());

        if (updatedRows == 0) {
            throw new ApplicationException("Promotion không tồn tại hoặc đã hết lượt sử dụng");
        }

        Promotion promotion = promotionRepository.findByCode(form.getCode())
                .orElseThrow(() -> new ApplicationException("Không tìm thấy Promotion"));

        return promotionMapper.toResponse(promotion);
    }

//    @Override
//    @Transactional
//    public PromotionResponse deletePromotionById(String id) {
//        promotionRepository.softDeletedById(id);
//        Promotion promotion = promotionRepository.findById(id)
//                .orElseThrow(()-> new RuntimeException("Không tìm thấy Promotion"));
//
//        return promotionMapper.toResponse(promotion);
//    }
}
