package com.y2k.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "analisis")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Analisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resultado;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_tipoAnalisis", referencedColumnName = "id")
    private TipoAnalisis tipoAnalisis;

    @ManyToOne
    @JoinColumn(name = "id_consulta", referencedColumnName = "id")
    private Consulta consulta;
}
