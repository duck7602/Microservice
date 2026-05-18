package com.ecommerce.productservice.service.impl;

import com.ecommerce.productservice.dto.request.ProductFIlterForm;
import com.ecommerce.productservice.dto.request.ProductRequest;
import com.ecommerce.productservice.dto.response.ProductResponse;
import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.event.ProductCreatedEvent;
import com.ecommerce.productservice.exception.ApplicationException;
import com.ecommerce.productservice.mapper.ProductMapper;
import com.ecommerce.productservice.repository.IProductRepository;
import com.ecommerce.productservice.service.ICategoryService;
import com.ecommerce.productservice.service.IProductService;
import com.ecommerce.productservice.specification.ProductSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ICategoryService categoryService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public List<ProductResponse> searchProduct(ProductFIlterForm form) {
        Specification<Product> specification = ProductSpecification.buildWhere(form);
        return productMapper.toResponseList(productRepository.findAll(specification));
    }

    @Override
    public ProductResponse deleteProduct(String id) throws JsonProcessingException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Không tìm thấy Product"));
        product.setIsDeleted(1);

        // kafka
        ProductCreatedEvent productCreatedEvent = productMapper.toEventCreated(product);
        String eventString = objectMapper.writeValueAsString(productCreatedEvent);
        kafkaTemplate.send("product_deleted",productCreatedEvent.getId(),eventString);
        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        if(productRepository.existsByName(request.getName())){
            throw new ApplicationException("Tên đã tồn tại.");
        }

        if (!categoryService.existsById(request.getCategoryId())){
            throw new ApplicationException("Không tồn tại Category");
        }
        Product product = productMapper.toEntity(request);
        product.setIsDeleted(0);
        Product saved = productRepository.save(product);

        // kafka
        ProductCreatedEvent event = productMapper.toEventCreated(saved);
        try {
            String eventString = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("product_created", eventString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return productMapper.toResponse(saved);
    }

    @Override
    @Cacheable(value = "product_detail", key = "#id", cacheManager = "redisCacheManager")
    public ProductResponse getById(String id) {
        try {
            log.info("Demo calculate so slow");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ApplicationException("Không tìm thấy Product"));
        return productMapper.toResponse(product);
    }

    @Override
    @CacheEvict(
            value = "product_detail",
            key = "#id",
            cacheManager = "redisCacheManager"
    )
    public void updateProduct(ProductRequest productRequest, String id) {
        Product product = productMapper.toEntity(productRequest);
        product.setId(id);
        productRepository.save(product);
    }
}
