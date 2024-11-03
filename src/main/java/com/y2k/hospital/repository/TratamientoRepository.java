package com.y2k.hospital.repository;

import com.y2k.hospital.entity.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
}
