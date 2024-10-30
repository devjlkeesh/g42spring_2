package dev.jlkeesh.module9.service;

import dev.jlkeesh.module9.dto.auth.AuthUserCreateDto;
import dev.jlkeesh.module9.dto.auth.GenerateTokenRequest;
import dev.jlkeesh.module9.dto.auth.RefreshTokenRequest;
import dev.jlkeesh.module9.dto.auth.TokenResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthUserService {

    TokenResponse generateAccessToken(GenerateTokenRequest dto);

    Long createUser( AuthUserCreateDto dto);

    TokenResponse refreshToken(RefreshTokenRequest dto);

    UserDetails getMe();
}
