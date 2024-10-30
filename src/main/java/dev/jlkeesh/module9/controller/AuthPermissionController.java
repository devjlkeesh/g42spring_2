package dev.jlkeesh.module9.controller;

import dev.jlkeesh.module9.entity.AuthPermission;
import dev.jlkeesh.module9.dto.auth.AuthPermissionCreateDto;
import dev.jlkeesh.module9.repository.AuthPermissionRepository;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/permission")
@PreAuthorize("hasRole('ADMIN')")
public class AuthPermissionController {

    private final AuthPermissionRepository authPermissionRepository;

    public AuthPermissionController(AuthPermissionRepository authPermissionRepository) {
        this.authPermissionRepository = authPermissionRepository;
    }

    @PostMapping
    public Integer create(@Valid @RequestBody AuthPermissionCreateDto dto) {
        AuthPermission authPermission = new AuthPermission();
        authPermission.setName(dto.name());
        authPermission.setDescription(dto.description());
        authPermissionRepository.save(authPermission);
        return authPermission.getId();
    }

    @GetMapping
    public List<AuthPermission> findAll() {
        return authPermissionRepository.findAll();
    }

}
