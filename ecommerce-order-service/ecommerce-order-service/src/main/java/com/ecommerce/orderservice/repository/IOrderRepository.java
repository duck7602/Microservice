package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IOrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {

    @Query("UPDATE Order o SET o.isDeleted = 1 WHERE o.id = ?1")
    @Modifying(clearAutomatically = true)
    void deleteByIdV2(String id);
}
