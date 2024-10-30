package dev.jlkeesh.module9.dto.auth;

import dev.jlkeesh.module9.entity.AuthUser;

import java.io.Serializable;

/**
 * DTO for {@link AuthUser}
 */
public record AuthUserCreateDto(String username, String password, String email) implements Serializable {
}