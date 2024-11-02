package dev.jlkeesh.module9.dto.auth;

import dev.jlkeesh.module9.entity.AuthRole;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link AuthRole}
 */
public record AuthRoleCreateDto(@NotBlank(message = "name can not be blank") String authRoleName,
                                @NotBlank(message = "description can not be blank") String description) implements Serializable {
}