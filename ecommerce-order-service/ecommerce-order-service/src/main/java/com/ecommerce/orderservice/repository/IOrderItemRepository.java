package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IOrderItemRepository extends JpaRepository<OrderItem, String>, JpaSpecificationExecutor<OrderItem> {

    @Query("UPDATE OrderItem o SET o.isDeleted = 1 WHERE o.id = ?1")
    @Modifying(clearAutomatically = true)
    void softDeletedById(String id);
}
