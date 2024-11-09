package dev.jlkeesh.module9.controller;

import dev.jlkeesh.module9.dto.BaseResponse;
import dev.jlkeesh.module9.dto.PageDto;
import dev.jlkeesh.module9.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;


    @GetMapping("/page")
    public BaseResponse<PageDto<Product>> getPage() {
        return productService.getPage();
    }

    @GetMapping("/{id}")
    public BaseResponse<Product> get(@PathVariable long id) {
        Product product = productService.get(id);
        return new BaseResponse<>(product);
    }

    @PostMapping("/create")
    public BaseResponse<Long> create(@RequestBody Product product) {
        Long newId = productService.create(product);
        return new BaseResponse<>(newId);
    }
    @PutMapping("/update")
    public BaseResponse<Long> create(@RequestBody Product product) {
        Long newId = productService.create(product);
        return new BaseResponse<>(newId);
    }
}
