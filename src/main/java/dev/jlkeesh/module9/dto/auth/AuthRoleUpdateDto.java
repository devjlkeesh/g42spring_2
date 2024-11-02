package dev.jlkeesh.module9.dto.auth;

import dev.jlkeesh.module9.entity.AuthRole;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link AuthRole}
 */
public record AuthRoleUpdateDto(String name, String description) implements Serializable {
}