package dev.jlkeesh.module9.dto.product;

import dev.jlkeesh.module9.entity.Product;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
public record ProductUpdateDto(Long id, String name, String description, long price) implements Serializable {
}