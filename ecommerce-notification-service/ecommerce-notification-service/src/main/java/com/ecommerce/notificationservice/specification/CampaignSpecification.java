package com.ecommerce.notificationservice.specification;

import com.ecommerce.notificationservice.dto.request.CampaignFilterForm;
import com.ecommerce.notificationservice.entity.Campaign;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public class CampaignSpecification {
    private static final String SEARCH = "search";

    public static Specification<Campaign> buildWhere(CampaignFilterForm form) {
        SpecificationImpl search = new SpecificationImpl(SEARCH, form.getSearch());
        return search;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpecificationImpl implements Specification<Campaign> {

        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Campaign> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null) {
                return null;
            }

            switch (key) {
                case SEARCH:
                    return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
                default:
                    return null;
            }
        }
    }
}
