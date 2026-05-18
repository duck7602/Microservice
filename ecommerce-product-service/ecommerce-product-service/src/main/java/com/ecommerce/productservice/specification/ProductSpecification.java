package com.ecommerce.productservice.specification;

import com.ecommerce.productservice.dto.request.ProductFIlterForm;
import com.ecommerce.productservice.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification {

    private static final String SEARCH = "search";
    private static final String ID_IN = "idIn";

    public static Specification<Product> buildWhere(ProductFIlterForm form) {
        SpecificationImpl search = new SpecificationImpl(SEARCH, form.getSearch());
        SpecificationImpl idIn = new SpecificationImpl(ID_IN, form.getIds());
        return search.and(idIn);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpecificationImpl implements Specification<Product> {

        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null) {
                return criteriaBuilder.conjunction();
            }

            switch (key) {
                case SEARCH:
                    return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
                case ID_IN:
                    return root.get("id").in((List<String>)value);
                default:
                    return null;
            }
        }
    }
}
