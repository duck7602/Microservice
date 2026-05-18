package com.ecommerce.inventoryservice.repository;

import com.ecommerce.inventoryservice.entity.Inventory;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IIventoryRepository extends JpaRepository<Inventory, String>, JpaSpecificationExecutor<Inventory> {
    boolean existsByProductId(String productId);

    List<Inventory> findByProductIdIn(List<String> productIds);
}
