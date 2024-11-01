package com.y2k.hospital.initializer;

import com.y2k.hospital.entity.Role;
import com.y2k.hospital.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RoleInitializer {
    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        Arrays.stream(Role.RolName.values()).forEach(rolName -> {
            if (!roleRepository.existsByName(rolName)) {
                roleRepository.save(new Role(null, rolName));
            }
        });
    }
}