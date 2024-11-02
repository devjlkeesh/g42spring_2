package dev.jlkeesh.module9.controller;

import dev.jlkeesh.module9.criteria.AuthRoleCriteria;
import dev.jlkeesh.module9.dto.ErrorData;
import dev.jlkeesh.module9.dto.PageDto;
import dev.jlkeesh.module9.dto.auth.AuthRoleCreateDto;
import dev.jlkeesh.module9.entity.AuthRole;
import dev.jlkeesh.module9.repository.AuthRoleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/role")
@PreAuthorize("hasRole('admin')")
@RequiredArgsConstructor
@Tag(name = "Auth Role Controller", description = "this group is responsible ....")
public class AuthRoleController {

    private final AuthRoleRepository authRoleRepository;


    @Deprecated
    @PostMapping(consumes = {
            "application/json",
            "application/xml"
    })
    public Integer create(@Valid @RequestBody AuthRoleCreateDto dto) {
        AuthRole authRole = new AuthRole();
        authRole.setName(dto.name());
        authRole.setDescription(dto.description());
        authRoleRepository.save(authRole);
        return authRole.getId();
    }

    @Operation(summary = "this api returns all roles",
            responses = {
                    @ApiResponse(description = "when everything ok", responseCode = "200"),
                    @ApiResponse(description = "when can not read from db", responseCode = "500",
                            content = {
                                    @Content(schema = @Schema(implementation = ErrorData.class))
                            }),
            }
    )
    @GetMapping
    public PageDto<AuthRole> findAll(AuthRoleCriteria criteria) {
        Pageable pageable = PageRequest.of(
                criteria.getPage(),
                criteria.getSize(),
                criteria.getSort()
        );
        Page<AuthRole> page = authRoleRepository.findAll(pageable);
        return new PageDto<>(page);
    }

}
