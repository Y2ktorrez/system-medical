package com.y2k.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "pago")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private Integer costoTotal;

    @ManyToOne
    @JoinColumn(name = "id_tipoPago", referencedColumnName = "id")
    private TipoPago tipoPago;

    @ManyToOne
    @JoinColumn(name = "id_consulta", referencedColumnName = "id")
    private Consulta consulta;
}
