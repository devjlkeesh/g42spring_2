package dev.jlkeesh.module9.product;

import dev.jlkeesh.module9.generic.PageDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProductDao {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final EntityManager entityManager;


    public PageDto<ProductDto> getPage(ProductCriteria criteria) {
        Map<String, Object> params = new HashMap<>();
        String query = "select t from Product t where 1=1 ";
        String countQuery = "select count(1) from Product t where 1=1 ";
        if (criteria.getProductType() != null) {
            query += " and lower(productType) = lower(:productType)";
            countQuery += " and lower(productType) = lower(:productType)";
            params.put("productType", criteria.getProductType().name());
        }
        if (criteria.getPriceFrom() != null) {
            query += " and price >= :priceFrom";
            countQuery += " and price >= :priceFrom";
            params.put("priceFrom", criteria.getPriceFrom());
        }
        if (criteria.getPriceTo() != null) {
            query += " and price <= :priceTo";
            countQuery += " and price <= :priceTo";
            params.put("priceTo", criteria.getPriceTo());
        }

        TypedQuery<Product> typeQuery = entityManager.createQuery(query, Product.class);
        params.forEach(typeQuery::setParameter);
        List<Product> products = typeQuery
                .setMaxResults(criteria.getSize())
                .setFirstResult(criteria.getPage() * criteria.getSize())
                .getResultList();
        TypedQuery<Long> countTypedQuery = entityManager.createQuery(countQuery, Long.class);
        params.forEach(countTypedQuery::setParameter);

        Long count = countTypedQuery.getSingleResult();

        int totalPage = count / criteria.getSize() + count % criteria.getSize() == 0 ? 0 : 1;
        return new PageDto<>(
                criteria.getPage() == 0,
                totalPage - 1 == criteria.getPage(),
                totalPage,
                count,
                criteria.getPage(),
                products.size(),
                productMapper.toDto(products)
        );
    }
}
