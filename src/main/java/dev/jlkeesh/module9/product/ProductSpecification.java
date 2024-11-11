package dev.jlkeesh.module9.product;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * {@link https://github.com/AhmetAksunger/Jpa-Specifications-Example}
 */
public class ProductSpecification implements Specification<Product> {

    private final ProductCriteria criteria;

    public ProductSpecification(ProductCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> qb, CriteriaBuilder cb) {
        Predicate p = cb.conjunction();
        if (criteria.getProductType() != null) {
            p.getExpressions().add(cb.equal(root.get("productType"), criteria.getProductType()));
        }
        if (criteria.getPriceTo() != null) {
            p.getExpressions().add(cb.lessThanOrEqualTo(root.get("price"), criteria.getPriceTo()));
        }
        if (criteria.getPriceFrom() != null) {
            p.getExpressions().add(cb.greaterThanOrEqualTo(root.get("price"), criteria.getPriceFrom()));
        }
        return p;
    }

}
