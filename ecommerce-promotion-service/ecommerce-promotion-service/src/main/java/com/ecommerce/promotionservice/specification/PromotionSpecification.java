package com.ecommerce.promotionservice.specification;

import com.ecommerce.promotionservice.dto.request.PromotionFilterForm;
import com.ecommerce.promotionservice.entity.Promotion;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.Date;

public class PromotionSpecification {

    private static final String SEARCH = "search";
    private static final String MIN_START_DATE = "minStartDate";
    private static final String FIND_BY_CODE = "findByCode";

    public static Specification<Promotion> buildWhere(PromotionFilterForm form) {
        SpecificationImpl search = new SpecificationImpl(SEARCH, form.getSearch());
        SpecificationImpl minStartDate = new SpecificationImpl(MIN_START_DATE, form.getMinStartDate());
        SpecificationImpl findById = new SpecificationImpl(FIND_BY_CODE, form.getCode());
        return search.and(minStartDate).and(findById);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpecificationImpl implements Specification<Promotion> {

        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Promotion> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null) {
                return null;
            }

            switch (key) {
                case SEARCH:
                    return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
                case MIN_START_DATE:
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), (Instant) value);
                case FIND_BY_CODE:
                    return criteriaBuilder.equal(root.get("code"), value.toString());
                default:
                    return null;
            }
        }
    }
}
