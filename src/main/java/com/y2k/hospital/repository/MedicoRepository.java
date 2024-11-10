package com.y2k.hospital.repository;

import com.y2k.hospital.entity.Medico;
import com.y2k.hospital.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByUser(User user);
}
