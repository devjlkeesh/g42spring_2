package dev.jlkeesh.module9.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record GenerateTokenDto(
        @NotBlank(message = "username can not be blank") String username,
        @NotBlank(message = "password can not be blank") String password) {

}
