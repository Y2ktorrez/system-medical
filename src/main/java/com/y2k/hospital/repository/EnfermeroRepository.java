package com.y2k.hospital.repository;

import com.y2k.hospital.entity.Enfermero;
import com.y2k.hospital.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EnfermeroRepository extends JpaRepository<Enfermero, Long> {
    Optional<Enfermero> findByUser(User user);
}
