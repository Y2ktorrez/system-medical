package com.y2k.hospital.repository;

import com.y2k.hospital.entity.Paciente;
import com.y2k.hospital.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByUser(User user);
}
