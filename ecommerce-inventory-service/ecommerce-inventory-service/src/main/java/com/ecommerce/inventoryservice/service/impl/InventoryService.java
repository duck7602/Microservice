package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.dto.request.InventoryRequest;
import com.ecommerce.inventoryservice.dto.request.IventoryFilterForm;
import com.ecommerce.inventoryservice.dto.request.LockProductItemRequest;
import com.ecommerce.inventoryservice.dto.request.LockProductRequest;
import com.ecommerce.inventoryservice.dto.response.InventoryResponse;
import com.ecommerce.inventoryservice.entity.Inventory;
import com.ecommerce.inventoryservice.exception.ApplicationException;
import com.ecommerce.inventoryservice.mapper.InventoryMapper;
import com.ecommerce.inventoryservice.repository.IIventoryRepository;
import com.ecommerce.inventoryservice.service.IInventoryService;
import com.ecommerce.inventoryservice.specification.InventorySpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService implements IInventoryService {

    private final IIventoryRepository iventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final RedissonClient redissonClient;

    @Override
    public List<InventoryResponse> filterInventory(IventoryFilterForm form) {
        Specification<Inventory> specification = InventorySpecification.buildWhere(form);
        return inventoryMapper.toResponseList(iventoryRepository.findAll(specification));
    }

    @Override
    public InventoryResponse createInventory(InventoryRequest inventoryRequest) {
        if(iventoryRepository.existsByProductId(inventoryRequest.getProductId())){
            throw new ApplicationException("Inventory đã tồn tại");
        }
        Inventory inventory = inventoryMapper.toEntity(inventoryRequest);
        inventory.setQuantity(0);
        inventory.setIsDeleted(0);
        return inventoryMapper.toResponse(iventoryRepository.save(inventory));
    }

    @Override
    public InventoryResponse deleteInventory(String productId) {
        Inventory inventory = iventoryRepository.findById(productId)
                .orElseThrow(()-> new ApplicationException("Không tồn tại Inventory"));
        inventory.setIsDeleted(1);
        return inventoryMapper.toResponse(inventory);
    }

    @Override
    public Boolean lock(LockProductRequest lockProductRequest) {
        List<String> productIds = lockProductRequest
                .getLockProductItemRequests()
                .stream()
                .map(LockProductItemRequest::getProductId)
                .collect(Collectors.toList());

        Map<String, Integer> productIdQuantityMap = new HashMap<>();
        for(LockProductItemRequest lockProductItemRequest: lockProductRequest.getLockProductItemRequests()){
            productIdQuantityMap.put(lockProductItemRequest.getProductId(), lockProductItemRequest.getQuantity());
        }

        // Lock Redis
        Collections.sort(productIds);
        String lockKey = "lock:products:"+String.join(",", productIds);

        RLock lock = redissonClient.getLock(lockKey);

        try {
            if (lock.tryLock(10, 5, TimeUnit.SECONDS)) {
                log.info("Start lock products : {}", productIds);
                List<Inventory> inventories = iventoryRepository.findByProductIdIn(productIds);
                for (Inventory inventory : inventories) {
                    Integer newStock = inventory.getQuantity() - productIdQuantityMap.get(inventory.getProductId());
                    if (newStock < 0) {
                        throw new ApplicationException("Quantity out of stock");
                    }
                    inventory.setQuantity(newStock);
                }

                iventoryRepository.saveAll(inventories);
            }

        } catch (Exception ex) {
            log.error("Lock product error {}", ex.getMessage());
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

        return true;
    }
}
