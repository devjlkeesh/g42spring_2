package dev.jlkeesh.module9.service;

import dev.jlkeesh.module9.dto.auth.AuthUserCreateDto;
import dev.jlkeesh.module9.dto.auth.GenerateTokenDto;
import dev.jlkeesh.module9.dto.auth.RefreshTokenDto;
import dev.jlkeesh.module9.dto.auth.TokenResponseDto;
import dev.jlkeesh.module9.enums.JwtTokenType;
import jakarta.validation.Valid;

public interface AuthUserService {

    TokenResponseDto generateAccessToken(GenerateTokenDto dto);

    Long createUser( AuthUserCreateDto dto);

    TokenResponseDto refreshToken( RefreshTokenDto dto);
}
