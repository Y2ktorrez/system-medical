package com.y2k.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "preconsulta")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Preconsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;
    private Integer peso;
    private Integer altura;
    private Integer edad;
    private String sexo;
    private String presion;

    private LocalDate preconsultaTerminada;

    @ManyToOne
    @JoinColumn(name = "ci_enfermero", referencedColumnName = "ci")
    private Enfermero enfermero;

    @ManyToOne
    @JoinColumn(name = "id_ficha", referencedColumnName = "id")
    private Ficha ficha;

}
