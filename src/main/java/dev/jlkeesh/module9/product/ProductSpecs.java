package dev.jlkeesh.module9.product;

import dev.jlkeesh.module9.QueryUtil;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


public final class ProductSpecs {

    private ProductSpecs() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<Product> getSpecification(ProductCriteria criteria) {

        Specification<Product> specs = Specification.where(null);

        if (criteria.getProductType() != null) {
            specs = specs.and(QueryUtil.equals(root -> root.get(Product_.PRODUCT_TYPE), criteria.getProductType()));
        }
        if (criteria.getPriceFrom() != null) {
            specs = specs.and(QueryUtil.gte(root -> root.get(Product_.PRICE), criteria.getPriceFrom()));
        }
        if (criteria.getPriceTo() != null) {
            specs = specs.and(QueryUtil.lte(root -> root.get(Product_.PRICE), criteria.getPriceTo()));
        }
        if (criteria.getQuery() != null) {
            /*String pattern = "%" + criteria.getQuery().toLowerCase() + "%";
            Specification<Product> s = (root, qb, cb) -> {
                Predicate[] predicates = new Predicate[3];
                predicates[0] = cb.like(cb.lower(root.get(Product_.NAME)), pattern);
                predicates[1] = cb.like(cb.lower(root.get(Product_.DESCRIPTION)), pattern);
                predicates[2] = cb.like(cb.lower(root.get(Product_.PRODUCT_TYPE)), pattern);
                return cb.or(predicates);
            };
*/
            specs = specs.and(QueryUtil.search(criteria.getQuery(), Product_.name, Product_.description));
        }
        return specs;
    }


    public static Specification<Product> productTypeSpecification(ProductType productType) {
        return (root, qb, cb) -> cb.equal(root.get(Product_.PRODUCT_TYPE), productType);
    }

    public static Specification<Product> priceFromSpecification(Long priceFrom) {
        return (root, qb, cb) -> cb.greaterThanOrEqualTo(root.get(Product_.PRICE), priceFrom);
    }

    public static Specification<Product> priceToSpecification(Long priceTo) {
        return (root, qb, cb) -> cb.lessThanOrEqualTo(root.get(Product_.PRICE), priceTo);
    }

}
