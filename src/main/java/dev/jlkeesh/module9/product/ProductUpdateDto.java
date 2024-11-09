package dev.jlkeesh.module9.product;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
public record ProductUpdateDto(String name, String description, long price) implements Serializable {
}