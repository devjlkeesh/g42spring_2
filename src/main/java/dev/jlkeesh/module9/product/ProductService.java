package dev.jlkeesh.module9.product;

import dev.jlkeesh.module9.generic.PageDto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductDao productDao;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, EntityManager entityManager, ProductDao productDao) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productDao = productDao;
    }

    @Transactional
    public PageDto<ProductDto> getPage(ProductCriteria criteria) {
//        ProductSpecification specification = new ProductSpecification(criteria);
        Page<ProductDto> page = productRepository.findPage(criteria).map(productMapper::toDto);
        return new PageDto<>(page);
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
