package com.y2k.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "insumoMedico")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsumoMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Integer costo;

    @ManyToOne
    @JoinColumn(name = "id_tipoInsumo", referencedColumnName = "id")
    private TipoInsumo tipoInsumo;

}
