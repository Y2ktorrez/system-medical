package com.y2k.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "medico")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Medico {
    @Id
    private Long ci;

    private Integer edad;
    private LocalDate fechaNacimiento;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "especialidad_medico",
            joinColumns = @JoinColumn(name = "ci_medico", referencedColumnName = "ci"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidad")

    )
    private Set<Especialidad> especialidades;
}
