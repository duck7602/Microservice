package com.ecommerce.productservice.specification;

import com.ecommerce.productservice.dto.request.CategoryFilterForm;
import com.ecommerce.productservice.entity.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {

    private static final String SEARCH = "search";

    public static Specification<Category> buildWhere(CategoryFilterForm form){
        SpecificationImpl search = new SpecificationImpl(SEARCH, form.getSearch());
        return search;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpecificationImpl implements Specification<Category>{

        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null){
                return criteriaBuilder.conjunction();
            }

            switch (key){
                case SEARCH:
                    return criteriaBuilder.like(root.get("name"), "%"+value.toString()+"%");
                default:
                    return null;
            }
        }
    }
}
