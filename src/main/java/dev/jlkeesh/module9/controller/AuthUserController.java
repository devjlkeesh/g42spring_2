package dev.jlkeesh.module9.controller;


import dev.jlkeesh.module9.dto.BaseResponse;
import dev.jlkeesh.module9.dto.auth.AuthUserCreateDto;
import dev.jlkeesh.module9.dto.auth.GenerateTokenRequest;
import dev.jlkeesh.module9.dto.auth.RefreshTokenRequest;
import dev.jlkeesh.module9.dto.auth.TokenResponse;
import dev.jlkeesh.module9.service.AuthUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthUserController {
    private final AuthUserService authUserService;

    public AuthUserController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/token")
    public BaseResponse<TokenResponse> generateToken(@Valid @RequestBody GenerateTokenRequest requestBody) {
        TokenResponse response = authUserService.generateAccessToken(requestBody);
        return new BaseResponse<>(response);
    }

    @PostMapping("/refresh")
    public BaseResponse<TokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest requestBody) {
        TokenResponse response = authUserService.refreshToken(requestBody);
        return new BaseResponse<>(response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<Long> create(@Valid @RequestBody AuthUserCreateDto dto) {
        Long id = authUserService.createUser(dto);
        return new BaseResponse<>(id);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<Object> getMe() {
        UserDetails userDetails = authUserService.getMe();
        return new BaseResponse<>(userDetails);
    }


}