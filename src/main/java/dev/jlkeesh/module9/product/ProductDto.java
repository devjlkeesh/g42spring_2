package dev.jlkeesh.module9.product;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Product}
 */
public record ProductDto(LocalDateTime createdAt, Long createdBy, LocalDateTime updatedAt, Long updatedBy, Long id,
                         String name, String description, long price) implements Serializable {
}