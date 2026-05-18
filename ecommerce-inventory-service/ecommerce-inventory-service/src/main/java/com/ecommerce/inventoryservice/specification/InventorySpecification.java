package com.ecommerce.inventoryservice.specification;

import com.ecommerce.inventoryservice.dto.request.IventoryFilterForm;
import com.ecommerce.inventoryservice.entity.Inventory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class InventorySpecification {

    private static final String IN_ID = "inId";
    private static final String MIN_QUANTITY = "minQuantity";
    private static final String MAX_QUANTITY = "maxQuantity";

    public static Specification<Inventory> buildWhere(IventoryFilterForm form){
        SpecificationImpl minQuantity = new SpecificationImpl(MIN_QUANTITY, form.getMinQuantiy());
        SpecificationImpl maxQuantity = new SpecificationImpl(MAX_QUANTITY, form.getMaxQuantity());
        SpecificationImpl inId = new SpecificationImpl(IN_ID, form.getIds());
        return minQuantity.and(maxQuantity).and(inId);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpecificationImpl implements Specification<Inventory>{

        private String key;
        private Object value;


        @Override
        public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if(value == null){
                return criteriaBuilder.conjunction();
            }
            switch (key){
                case MIN_QUANTITY:
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("quantity"), (Integer) value);
                case MAX_QUANTITY:
                    return criteriaBuilder.lessThanOrEqualTo(root.get("quantity"), (Integer) value);
                case IN_ID:
                    return root.get("productId").in((List<String>) value);
                default:
                    return null;
            }
        }
    }
}
