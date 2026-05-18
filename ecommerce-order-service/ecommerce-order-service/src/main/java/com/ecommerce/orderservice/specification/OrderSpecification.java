package com.ecommerce.orderservice.specification;

import com.ecommerce.orderservice.dto.request.OrderFilterForm;
import com.ecommerce.orderservice.entity.Order;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {

    private static final String MIN_TOTAL_AMOUNT = "minTotalAmount";
    private static final String MAX_TOTAL_AMOUNT = "maxTotalAmount";

    public static Specification<Order> buildWhere(OrderFilterForm form){
            SpecificationImple minTotalAmount = new SpecificationImple(MIN_TOTAL_AMOUNT, form.getMinTotalAmount());
            SpecificationImple maxTotalAmount = new SpecificationImple(MAX_TOTAL_AMOUNT, form.getMaxTotalAmount());
            return minTotalAmount.and(maxTotalAmount);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    private static class SpecificationImple implements Specification<Order>{
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if(value == null){
                return null;
            }

            switch (key){
                case MIN_TOTAL_AMOUNT:
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("totalAmount"),(Integer) value);
                case MAX_TOTAL_AMOUNT:
                    return criteriaBuilder.lessThanOrEqualTo(root.get("totalAmount"), (Integer)value);
                default:
                    return null;
            }
        }
    }
}
