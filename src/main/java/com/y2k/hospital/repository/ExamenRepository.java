package com.y2k.hospital.repository;

import com.y2k.hospital.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamenRepository extends JpaRepository<Examen, Long> {
}
