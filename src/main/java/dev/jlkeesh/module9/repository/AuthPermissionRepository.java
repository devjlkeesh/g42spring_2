package dev.jlkeesh.module9.repository;

import dev.jlkeesh.module9.entity.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthPermissionRepository extends JpaRepository<AuthPermission, Integer> {
}