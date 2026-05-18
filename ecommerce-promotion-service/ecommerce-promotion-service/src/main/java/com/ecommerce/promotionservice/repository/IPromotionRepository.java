package com.ecommerce.promotionservice.repository;

import com.ecommerce.promotionservice.entity.Promotion;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IPromotionRepository extends JpaRepository<Promotion, String>, JpaSpecificationExecutor<Promotion> {

    boolean existsByCode(@NotEmpty String code);

    boolean existsByCodeAndIdNot(@NotEmpty String code, String id);

    @Modifying
    @Query("""
                UPDATE Promotion p
                SET p.usageLimit = p.usageLimit - 1
                WHERE p.code = :code
                AND p.usageLimit > 0
            """)
    int decrementUsageLimit(@Param("code") String code);

    Optional<Promotion> findByCode(String code);
}
