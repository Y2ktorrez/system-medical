package com.y2k.hospital.repository;

import com.y2k.hospital.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Role.RolName name);
    boolean existsByName(Role.RolName name);
}
