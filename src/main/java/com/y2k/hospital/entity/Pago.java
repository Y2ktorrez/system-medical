package com.y2k.hospital.entity;

import com.y2k.hospital.Enum.TipoPago;
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

    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago;

    @Column(nullable = false)
    private boolean cancelado;

    @ManyToOne
    @JoinColumn(name = "id_consulta", referencedColumnName = "id")
    private Consulta consulta;
}
