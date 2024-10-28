package dev.jlkeesh.module9.controller;


import dev.jlkeesh.module9.config.security.JwtTokenUtil;
import dev.jlkeesh.module9.dto.AuthUserCreateDto;
import dev.jlkeesh.module9.dto.auth.TokenRequest;
import dev.jlkeesh.module9.entity.AuthUser;
import dev.jlkeesh.module9.repository.AuthUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final AuthUserRepository authUserRepository;

    public AuthController(
            JwtTokenUtil jwtTokenUtil,
            AuthenticationManager authenticationManager, AuthUserRepository authUserRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.authUserRepository = authUserRepository;
    }

    @PostMapping("/token")
    public String token(@RequestBody TokenRequest tokenRequest) {
        String username = tokenRequest.username();
        String password = tokenRequest.password();
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authentication);
        return jwtTokenUtil.generateToken(username);
    }

    @PostMapping
    public Long create(@RequestBody AuthUserCreateDto dto) {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(dto.username());
        authUser.setPassword(dto.password());
        authUser.setEmail(dto.email());
        authUserRepository.save(authUser);
        return authUser.getId();
    }


}