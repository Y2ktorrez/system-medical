package com.y2k.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "paciente")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    @Id
    private Long ci;
    private LocalDate fechaNacimiento;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "paciente")
    private List<Ficha> fichas;
}
