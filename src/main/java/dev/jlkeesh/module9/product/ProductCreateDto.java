package dev.jlkeesh.module9.product;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
public record ProductCreateDto(String name, String description, long price, ProductType productType
) implements Serializable {
}