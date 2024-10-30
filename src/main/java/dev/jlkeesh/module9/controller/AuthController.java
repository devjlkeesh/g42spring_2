package dev.jlkeesh.module9.controller;


import dev.jlkeesh.module9.dto.BaseResponse;
import dev.jlkeesh.module9.dto.auth.AuthUserCreateDto;
import dev.jlkeesh.module9.dto.auth.GenerateTokenDto;
import dev.jlkeesh.module9.dto.auth.TokenResponseDto;
import dev.jlkeesh.module9.service.AuthUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthUserService authUserService;

    public AuthController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/token")
    public BaseResponse<TokenResponseDto> generateToken(@Valid @RequestBody GenerateTokenDto dto) {
        TokenResponseDto responseDto = authUserService.generateAccessToken(dto);
        return new BaseResponse<>(responseDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<Long> create(@Valid @RequestBody AuthUserCreateDto dto) {
        Long id = authUserService.createUser(dto);
        return new BaseResponse<>(id);
    }


}