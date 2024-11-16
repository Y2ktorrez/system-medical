package com.y2k.hospital.repository;

import com.y2k.hospital.entity.InsumoTratamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsumoTratamientoRepository extends JpaRepository<InsumoTratamiento, Long> {
    List<InsumoTratamiento> findByTratamientoId(Long tratamientoId);

}
