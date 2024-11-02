package dev.jlkeesh.module9.mapper;

import dev.jlkeesh.module9.entity.AuthPermission;
import dev.jlkeesh.module9.entity.AuthPermissionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthPermissionMapper {
    AuthPermissionDto toDto(AuthPermission authPermission);

    List<AuthPermissionDto> toDto(List<AuthPermission> authPermissions);
}
