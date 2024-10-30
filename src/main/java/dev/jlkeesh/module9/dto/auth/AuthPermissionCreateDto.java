package dev.jlkeesh.module9.dto.auth;

import dev.jlkeesh.module9.entity.AuthPermission;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link AuthPermission}
 */
public record AuthPermissionCreateDto(@NotBlank(message = "name can not be blank") String name,
                                      @NotBlank(message = "description can not be blank") String description)
        implements Serializable {
}