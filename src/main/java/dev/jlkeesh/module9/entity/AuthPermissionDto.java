package dev.jlkeesh.module9.entity;

import java.io.Serializable;

/**
 * DTO for {@link AuthPermission}
 */
public record AuthPermissionDto(Long createdBy, Long updatedBy, Integer id, String name,
                                String description) implements Serializable {
}