package dev.jlkeesh.module9.product;

import dev.jlkeesh.module9.generic.PageDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final EntityManager entityManager;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, EntityManager entityManager) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.entityManager = entityManager;
    }

    @Transactional
    public PageDto<ProductDto> getPage(ProductCriteria criteria) {
        PageRequest pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
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


        System.out.println("----------------------------------");
        Page<ProductDto> page = productRepository.findAll(pageable)
                .map(productMapper::toDto);
        PageDto<ProductDto> productDtoPageDto = new PageDto<>(page);
        System.out.println("productDtoPageDto = " + productDtoPageDto);
        System.out.println("----------------------------------");

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

    public ProductDto get(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found:" + id));
        return productMapper.toDto(product);
    }

    public Long create(ProductCreateDto dto) {
        Product product = productMapper.toEntity(dto);
        productRepository.save(product);
        return product.getId();
    }

    public Long update(Long id, ProductUpdateDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found:" + id));
        productMapper.partialUpdate(dto, product);
        productRepository.save(product);
        return product.getId();
    }
}
