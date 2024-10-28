package dev.jlkeesh.module9.repository;

import dev.jlkeesh.module9.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
}