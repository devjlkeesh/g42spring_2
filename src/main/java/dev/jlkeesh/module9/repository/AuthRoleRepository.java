package dev.jlkeesh.module9.repository;

import dev.jlkeesh.module9.entity.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Integer> {
}