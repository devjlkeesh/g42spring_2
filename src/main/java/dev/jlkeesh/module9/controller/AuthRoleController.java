package dev.jlkeesh.module9.controller;

import dev.jlkeesh.module9.entity.AuthRole;
import dev.jlkeesh.module9.dto.auth.AuthRoleCreateDto;
import dev.jlkeesh.module9.repository.AuthRoleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/role")
@PreAuthorize("hasRole('admin')")
@RequiredArgsConstructor
public class AuthRoleController {

    private final AuthRoleRepository authRoleRepository;


    @PostMapping
    public Integer create(@Valid @RequestBody AuthRoleCreateDto dto) {
        AuthRole authRole = new AuthRole();
        authRole.setName(dto.name());
        authRole.setDescription(dto.description());
        authRoleRepository.save(authRole);
        return authRole.getId();
    }

    @GetMapping
    public List<AuthRole> findAll() {
        return authRoleRepository.findAll();
    }

}
