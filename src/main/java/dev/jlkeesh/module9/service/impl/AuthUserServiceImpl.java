package dev.jlkeesh.module9.service.impl;

import dev.jlkeesh.module9.configuration.security.JwtTokenUtil;
import dev.jlkeesh.module9.dto.auth.AuthUserCreateDto;
import dev.jlkeesh.module9.dto.auth.GenerateTokenDto;
import dev.jlkeesh.module9.dto.auth.RefreshTokenDto;
import dev.jlkeesh.module9.dto.auth.TokenResponseDto;
import dev.jlkeesh.module9.entity.AuthUser;
import dev.jlkeesh.module9.enums.JwtTokenType;
import dev.jlkeesh.module9.repository.AuthUserRepository;
import dev.jlkeesh.module9.service.AuthUserService;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder bcryptPasswordEncoder;
    private final AuthUserRepository authUserRepository;

    public AuthUserServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, PasswordEncoder bcryptPasswordEncoder, AuthUserRepository authUserRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
        this.authUserRepository = authUserRepository;
    }

    @Override
    public TokenResponseDto generateAccessToken(GenerateTokenDto dto) {
        String username = dto.username();
        String password = dto.password();
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authentication);
        var accessTokenClaims = Map.<String, Object>of("token", JwtTokenType.ACCESS);
        var refreshTokenClaims = Map.<String, Object>of("token", JwtTokenType.REFRESH);
        String accessToken = jwtTokenUtil.generateAccessToken(username, accessTokenClaims);
        String refreshToken = jwtTokenUtil.generateRefreshToken(username, refreshTokenClaims);
        return new TokenResponseDto(accessToken, refreshToken);
    }

    @Override
    public Long createUser(AuthUserCreateDto dto) {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(dto.username());
        authUser.setPassword(bcryptPasswordEncoder.encode(dto.password()));
        authUser.setEmail(dto.email());
        authUserRepository.save(authUser);
        return authUser.getId();
    }

    @Override
    public TokenResponseDto refreshToken(RefreshTokenDto dto) {
        String refreshToken = dto.token();
        if (!jwtTokenUtil.isValid(refreshToken)) {
            throw new BadCredentialsException("refreshToken invalid");
        }
        Claims claims = jwtTokenUtil.getClaims(refreshToken);
        if (claims.get("token") == null || !claims.get("token").equals("REFRESH")) {
            throw new BadCredentialsException("refreshToken invalid");
        }
        var accessTokenClaims = Map.<String, Object>of("refreshToken", JwtTokenType.ACCESS);
        String username = claims.get("sub", String.class);
        String accessToken = jwtTokenUtil.generateAccessToken(username, accessTokenClaims);
        return new TokenResponseDto(accessToken, refreshToken);
    }
}
