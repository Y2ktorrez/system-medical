package com.y2k.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name="ficha")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaEmision;
    private LocalDate fechaAtencion;
    private LocalTime horaAtencion;

    @ManyToOne
    @JoinColumn(name="ci_paciente",referencedColumnName = "ci")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name="ci_medico",referencedColumnName = "ci")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name="id_especialidad",referencedColumnName = "id")
    private Especialidad especialidad;
}
