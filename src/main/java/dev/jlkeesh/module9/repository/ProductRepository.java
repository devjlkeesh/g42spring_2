package dev.jlkeesh.module9.repository;

import dev.jlkeesh.module9.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}