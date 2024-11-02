package dev.jlkeesh.module9.dto.auth;

import dev.jlkeesh.module9.entity.AuthPermissionDto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link dev.jlkeesh.module9.entity.AuthRole}
 */
public record AuthRoleDto(Long createdBy, Long updatedBy, Integer id, String name, String description,
                          List<AuthPermissionDto> permissions) implements Serializable {
}