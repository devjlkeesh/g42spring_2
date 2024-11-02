package dev.jlkeesh.module9.mapper;

import dev.jlkeesh.module9.dto.auth.AuthRoleCreateDto;
import dev.jlkeesh.module9.dto.auth.AuthRoleDto;
import dev.jlkeesh.module9.dto.auth.AuthRoleUpdateDto;
import dev.jlkeesh.module9.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {AuthPermissionMapper.class}
)
public interface AuthRoleMapper {

    @Mapping(target = "name", source = "authRoleName")
    AuthRole fromCreateDto(AuthRoleCreateDto dto);

    void fromUpdateDto(AuthRoleUpdateDto dto, @MappingTarget AuthRole authRole);

    AuthRoleDto toDto(AuthRole authRole);

}
