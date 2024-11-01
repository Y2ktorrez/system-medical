package com.y2k.hospital.initializer;

import com.y2k.hospital.entity.Role;
import com.y2k.hospital.entity.User;
import com.y2k.hospital.repository.RoleRepository;
import com.y2k.hospital.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static com.y2k.hospital.entity.Role.RolName.Administrador;

@Configuration
public class AdminInitializer {

    private final PasswordEncoder passwordEncoder;
    public AdminInitializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Transactional
    public CommandLineRunner createDefaultAdmin(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            String adminEmail = "admin@admin.com";
            Role adminRole = roleRepository.findByName(Administrador)
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName(Administrador);
                        return roleRepository.save(newRole);
                    });

            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                User adminUser = new User();
                adminUser.setNombre("Admin");
                adminUser.setEmail(adminEmail);
                adminUser.setPassword(passwordEncoder.encode("admin"));
                adminUser.setRoles(Set.of(adminRole));

                userRepository.save(adminUser);

                System.out.println("Administrador Creado.");
            } else {
                System.out.println("El administrador ya existe.");
            }
        };
    }
}

