package com.ecommerce.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory extends BaseEntity{

    @Id
    @Column(name = "product_id", length = 36)
    private String productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
