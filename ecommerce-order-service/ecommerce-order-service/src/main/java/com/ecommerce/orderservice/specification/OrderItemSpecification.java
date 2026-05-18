package com.ecommerce.orderservice.specification;

import com.ecommerce.orderservice.dto.request.OrderItemFilterForm;
import com.ecommerce.orderservice.entity.OrderItem;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;


public class OrderItemSpecification {

    private static final String MIN_PRICE = "minPrice";
    private static final String MAX_PRICE = "maxPrice";

    public static Specification<OrderItem> buildWhere(OrderItemFilterForm form){
            SpecificationImpl minPrice = new SpecificationImpl(MIN_PRICE, form.getMinPrice());
            SpecificationImpl maxPrice = new SpecificationImpl(MAX_PRICE, form.getMaxPrice());
            return minPrice.and(maxPrice);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpecificationImpl implements Specification<OrderItem>{
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<OrderItem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null){
                return null;
            }

            switch (key){
                case MIN_PRICE:
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), (Integer) value);
                case MAX_PRICE:
                    return criteriaBuilder.lessThanOrEqualTo(root.get("price"), (Integer) value);
                default:
                    return null;
            }
        }
    }
}
